package com.springboot.demo.commom.config;

import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import java.util.Date;

/**
 * @author scaf_xs
 * @ClassName: MySessionManage
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/3/5 16:25
 */

public class MySessionManage {


    /* 获取当前用户 */
    public static SysUser getCurrentUser() {
        SysUser sysUser;
        try {
            sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return sysUser;
    }


    /* 获取当前线程 */
    public static Session getCurrentSession() {
        Session session;
        try {
            session = SecurityUtils.getSubject().getSession();
            SecurityUtils.getSubject().getSession().getLastAccessTime();
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return session;
    }

    public static Date getLastLoginTime(){
        Date date;
        try{
            date=SecurityUtils.getSubject().getSession().getLastAccessTime();
        }catch (BusiException e){
            throw new BusiException(e.getMessage());
        }
        return date;
    }


}
