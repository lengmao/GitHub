package com.springboot.demo.sys.service;

import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.commom.util.JsonUtil;
import com.springboot.demo.sys.entity.SysMenu;
import com.springboot.demo.sys.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author scaf_xs
 * @ClassName: SysMenuService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/28 10:39
 */
@Service
public class SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    public List<SysMenu> getMenusByUser(String userId) {
        List<SysMenu> sysMenus;
        try {
            sysMenus = sysMenuMapper.getMenusByUser(userId);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return sysMenus;
    }

    public List<SysMenu> getAllMenu() {
        List<SysMenu> sysMenus;
        try {
            sysMenus = sysMenuMapper.getAllMenu();
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return sysMenus;
    }

    public void create(String beanJson) {
        SysMenu sysMenu = JsonUtil.jsonToBean(beanJson, SysMenu.class);
        try {
            sysMenu.setId(UUID.randomUUID().toString().replace("-", ""));
            sysMenuMapper.create(sysMenu);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

    public void delete(String code) {
        try {
            Integer count = sysMenuMapper.getRoleMenuByMenuCode(code);
            if (count == 0) {
                sysMenuMapper.deleteMenu(code);
            } else {
                throw new BusiException("请取消对应角色的该菜单权限，再进行删除!");
            }
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }
}
