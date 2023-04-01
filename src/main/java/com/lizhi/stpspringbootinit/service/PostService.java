package com.lizhi.stpspringbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lizhi.stpspringbootinit.model.dto.post.PostQueryRequest;
import com.lizhi.stpspringbootinit.model.entity.Post;
import com.lizhi.stpspringbootinit.model.vo.PostVO;


import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * 
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);



    /**
     * 获取帖子封装
     *
     * @param post
     * @param
     * @return
     */
    PostVO getPostVO(Post post);

    /**
     * 分页获取帖子封装
     *
     * @param postPage
     * @param
     * @return
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage);
}
