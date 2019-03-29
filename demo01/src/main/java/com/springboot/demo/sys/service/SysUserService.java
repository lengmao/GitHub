package com.springboot.demo.sys.service;

import com.springboot.demo.commom.config.MySessionManage;
import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.commom.util.JsonUtil;
import com.springboot.demo.commom.util.MD5Util;
import com.springboot.demo.sys.entity.SysUser;
import com.springboot.demo.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author scaf_xs
 * @ClassName: UserService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/26 14:31
 */
@Service
public class SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    public SysUser getUserByMap(Map<String, String> map) {
        SysUser sysUser;
        try {
            sysUser = sysUserMapper.getUserByMap(map);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return sysUser;
    }

    public Map<String, Object> getAllUsers(int pageNumber, int pageSize) {
        Map<String, Object> userMap = new HashMap();
        try {
            Map<String, Object> pageMap = new HashMap<>(10);
            pageMap.put("pageNumber", (pageNumber - 1) * pageSize);
            pageMap.put("pageSize", pageSize);
            List<SysUser> sysUsers = sysUserMapper.getAllUsers(pageMap);
            int count = sysUserMapper.getUsersCount();
            userMap.put("count", count);
            userMap.put("sysUsers", sysUsers);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return userMap;
    }

    public void create(String beanJson) {
        SysUser sysUser = JsonUtil.jsonToBean(beanJson, SysUser.class);
        try {
            sysUser.setId(UUID.randomUUID().toString().replace("-", ""));
            sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
            sysUser.setCreateTime(new Date());
            sysUser.setLastLoginTime(MySessionManage.getLastLoginTime());
            if ("true".equals(sysUser.getStatus())) {
                sysUser.setStatus("1");
            } else {
                sysUser.setStatus("0");
            }
            sysUserMapper.create(sysUser);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

    public synchronized void deleteUserRoles(String userId) {
        try {
            sysUserMapper.deleteUserRoles(userId);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

    public void assortUserRole(String userId, String[] roleIds) {
        try {
            if (roleIds.length > 0) {
                for (int i = 0; i < roleIds.length; i++) {
                    String id = UUID.randomUUID().toString().replace("-", "");
                    sysUserMapper.assortUserRole(id, userId, roleIds[i]);
                }
            }

        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

}
