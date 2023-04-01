package com.lizhi.stpspringbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizhi.stpspringbootinit.model.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户数据库操作
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * 
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




