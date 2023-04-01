package com.lizhi.stpspringbootinit.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.lizhi.stpspringbootinit.common.BaseResponse;
import com.lizhi.stpspringbootinit.common.DeleteRequest;
import com.lizhi.stpspringbootinit.common.ErrorCode;
import com.lizhi.stpspringbootinit.common.ResultUtils;
import com.lizhi.stpspringbootinit.exception.BusinessException;
import com.lizhi.stpspringbootinit.model.dto.post.PostAddRequest;
import com.lizhi.stpspringbootinit.model.dto.post.PostEditRequest;
import com.lizhi.stpspringbootinit.model.dto.post.PostQueryRequest;
import com.lizhi.stpspringbootinit.model.entity.Post;
import com.lizhi.stpspringbootinit.model.entity.User;
import com.lizhi.stpspringbootinit.model.vo.PostVO;
import com.lizhi.stpspringbootinit.service.PostService;
import com.lizhi.stpspringbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.lizhi.stpspringbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 帖子接口
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * 
 */
@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    private final static Gson GSON = new Gson();

    // region 增删改查

    /**
     * 创建
     *
     * @param postAddRequest
     * @param
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody PostAddRequest postAddRequest) {
        if (postAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postAddRequest, post);
        List<String> tags = postAddRequest.getTags();
        if (tags != null) {
            post.setTags(GSON.toJson(tags));
        }
        postService.validPost(post, true);
        User loginUser = (User) StpUtil.getSession().get(USER_LOGIN_STATE);
        post.setUserId(loginUser.getId());
        post.setFavourNum(0);
        post.setThumbNum(0);
        boolean result = postService.save(post);
        Validate.isTrue(result);
        long newPostId = post.getId();
        return ResultUtils.success(newPostId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @SaCheckRole(value = {"admin","user"},mode = SaMode.OR)
    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        Post oldPost = postService.getById(id);
        if (oldPost == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }


        boolean b = postService.removeById(id);
        return ResultUtils.success(b);
    }



    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<PostVO> getPostVOById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = postService.getById(id);
        if (post == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(postService.getPostVO(post));
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<PostVO>> listPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
            HttpServletRequest request) {
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getPageSize();
        // 限制爬虫

        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ResultUtils.success(postService.getPostVOPage(postPage));
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<PostVO>> listMyPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
            HttpServletRequest request) {
        if (postQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = (User) StpUtil.getSession().get(USER_LOGIN_STATE);
        postQueryRequest.setUserId(loginUser.getId());
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getPageSize();
        // 限制爬虫

        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ResultUtils.success(postService.getPostVOPage(postPage));
    }

    // endregion



    /**
     * 编辑（用户）
     *
     * @param postEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @SaCheckRole(value = {"admin","user"},mode = SaMode.OR)
    public BaseResponse<Boolean> editPost(@RequestBody PostEditRequest postEditRequest, HttpServletRequest request) {
        if (postEditRequest == null || postEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postEditRequest, post);
        List<String> tags = postEditRequest.getTags();
        if (tags != null) {
            post.setTags(GSON.toJson(tags));
        }
        // 参数校验
        postService.validPost(post, false);
        val loginId = StpUtil.getLoginId();
        if(loginId==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        boolean result = postService.updateById(post);
        return ResultUtils.success(result);
    }

}
