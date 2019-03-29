package com.springboot.demo.commom.config;

import com.springboot.demo.commom.util.MD5Util;
import com.springboot.demo.sys.entity.SysUser;
import com.springboot.demo.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: MyShiroRealm
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/26 15:30
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        /**
         * 继承AuthorizingRealm类，重写doGetAuthenticationInfo方法
         * 在doGetAuthenticationInfo方法中通过参数获取前端传入的name,password
         * 实现自己的name,password验证逻辑
         */
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Map<String, String> map = new HashMap<>(10);
        map.put("name", token.getUsername());
        String password=String.valueOf(token.getPassword());
        map.put("password", MD5Util.encode(password));
        SysUser sysUser = sysUserService.getUserByMap(map);
        if (null == sysUser) {
            throw new AccountException("账号或密码不正确!!");
        }
        if ("0".equals(sysUser.getStatus())) {
            throw new DisabledAccountException("此账号已经设置为禁止登录!!");
        }

        /**
         *验证通过后获取shiro默认创建的session，设置session过期时间
         */
        MySessionManage.getCurrentSession().setTimeout(180000L);
        return new SimpleAuthenticationInfo(sysUser, token.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
