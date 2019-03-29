package com.springboot.demo.sys.service;

import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.commom.util.JsonUtil;
import com.springboot.demo.sys.entity.SysMenu;
import com.springboot.demo.sys.entity.SysRole;
import com.springboot.demo.sys.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author scaf_xs
 * @ClassName: SysRoleService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/3/21 15:57
 */
@Service
public class SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    public Map<String, Object> getAllRoles(Map<String, Object> map) {
        Map<String, Object> roleMap = new HashMap<>(10);
        try {
            List<SysRole> sysRoles = sysRoleMapper.getAllRoles(map);
            int count = sysRoleMapper.getRolesCount();
            roleMap.put("count", count);
            roleMap.put("sysRoles", sysRoles);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return roleMap;
    }

    public List<SysMenu> getRoleMenu(String id) {
        List<SysMenu> list;
        try {
            list = sysRoleMapper.getRoleMenu(id);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return list;
    }

    public List<SysRole> getUserRole(String userId){
        List<SysRole> list;
        try{
            list=sysRoleMapper.getUserRole(userId);
        }catch (BusiException e){
            throw new BusiException(e.getMessage());
        }
        return list;
    }

    public void create(String beanJson) {
        SysRole sysRole = JsonUtil.jsonToBean(beanJson, SysRole.class);
        try {
            sysRole.setId(UUID.randomUUID().toString().replace("-", ""));
            sysRoleMapper.create(sysRole);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

    public synchronized void deleteRoleMenus(String roleId) {
        try {
            sysRoleMapper.deleteRoleMenus(roleId);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

    public void updatePower(String roleId, String[] menuIds) {
        try {
            if (menuIds.length > 0) {
                for (int i = 0; i < menuIds.length; i++) {
                    String id = UUID.randomUUID().toString().replace("-", "");
                    sysRoleMapper.updatePower(id, roleId, menuIds[i]);
                }
            }
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

}
