package com.lizhi.stpspringbootinit.common;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.lizhi.stpspringbootinit.model.entity.User;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.lizhi.stpspringbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合 
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add("101");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        // list.add("user.delete");
        list.add("art.*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //
        User user = (User) StpUtil.getSession().get(USER_LOGIN_STATE);
        val userRole = user.getUserRole();
        if(userRole.equals("admin")){
            List<String> list = new ArrayList<String>();
            list.add("admin");
            return list;
        }
        if(userRole.equals("user")){
            List<String> list = new ArrayList<String>();
            list.add("user");
            return list;
        }
        return new ArrayList<>();
    }

}
