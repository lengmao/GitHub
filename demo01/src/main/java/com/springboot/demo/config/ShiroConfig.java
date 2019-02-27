package com.springboot.demo.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: ShiroConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/26 14:39
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        System.out.println("Shiro拦截器注入..............");
        /**
         * 设置securityManage  注：必须设置
         */
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器
        Map<String,String> filter=new LinkedHashMap<>();

        /**
         * 配置不会被拦截的链接，顺序执行
         */
        filter.put("/assets/**","anon");
        filter.put("/submitLogin","anon");
        filter.put("/toLogin","anon");

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filter.put("/logout", "logout");

        /**
         * 需要拦截的链接，从上向下顺序执行，一般将 /**放在最为下边
         * authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
         */
        filter.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);

        /**
         * 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
         */
        shiroFilterFactoryBean.setLoginUrl("/login");

        /**
         * 登录成功要跳转的页面
         */
        shiroFilterFactoryBean.setSuccessUrl("/index");

        /**
         * 未授权的页面
         */
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return  shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();

        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm=new MyShiroRealm();
        return myShiroRealm;
    }
}
