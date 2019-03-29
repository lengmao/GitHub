package com.springboot.demo.sys.mapper;

import com.springboot.demo.sys.entity.SysMenu;
import com.springboot.demo.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * @author scaf_xs
 * @ClassName: SysRoleMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/3/21 15:56
 */
@Mapper
public interface SysRoleMapper {
    /**
     * 获取角色列表
     * @param map
     * @return
     */
    List<SysRole> getAllRoles(Map<String,Object> map);

    /**
     * 获取角色总数
     * @return
     */
    Integer getRolesCount();

    /**
     * 获取角色对应的菜单
     * @param id
     * @return
     */
    List<SysMenu> getRoleMenu(String id);

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    List<SysRole> getUserRole(String userId);
    /**
     * 创建角色
     * @param sysRole
     */
    void create(SysRole sysRole);
    /**
     * 修改角色菜单
     * @param id
     * @param roleId
     * @param menuId
     */
    void updatePower(@Param("id") String id, @Param("roleId") String roleId, @Param("menuId") String menuId);

    /**
     * 删除角色对应的菜单
     * @param roleId
     */
    void deleteRoleMenus(String roleId);
}
