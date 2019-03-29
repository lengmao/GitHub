package com.springboot.demo.sys.mapper;

import com.springboot.demo.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: SysMenuMapper
 * @Description: 菜单服务mapper
 * @date 2019/2/26 15:30
 */

@Mapper
public interface SysMenuMapper {
    /**
     * 获取用户菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> getMenusByUser(String userId);

    /**
     *获取所有菜单列表
     * @return
     */
    List<SysMenu> getAllMenu();

    /**
     * 创建用户
     * @param sysMenu
     */
    void create(SysMenu sysMenu);

    /**
     * 通过code获取菜单
     * @param code
     * @return
     */
    int getRoleMenuByMenuCode(String code);

    /**
     * 删除菜单
     * @param code
     */
    void deleteMenu(@Param("code") String code);
}
