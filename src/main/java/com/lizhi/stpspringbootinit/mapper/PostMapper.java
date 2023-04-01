package com.lizhi.stpspringbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizhi.stpspringbootinit.model.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * 
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




