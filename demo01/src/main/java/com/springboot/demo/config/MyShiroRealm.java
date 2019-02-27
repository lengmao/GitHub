package com.springboot.demo.config;

import com.springboot.demo.sys.entity.SysUser;
import com.springboot.demo.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        try {

        } catch (Exception e) {

        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            Map<String, String> map = new HashMap<>(10);
            map.put("name", token.getUsername());
            map.put("password", String.valueOf(token.getPassword()));
            SysUser sysUser = userService.getUserByMap(map);

            if (null == sysUser) {
                throw new AccountException("账号或密码不正确!!");
            } else if ("0".equals(sysUser.getStatus())) {
                throw new DisabledAccountException("此账号已经设置为禁止登录!!");
            } else {
                //登录成功
                sysUser.setLastLoginTime(new Date());
            }
        return new SimpleAuthenticationInfo(sysUser,token.getPassword(),getName());
    }
}
