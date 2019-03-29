package com.springboot.demo.sys.mapper;

import com.springboot.demo.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * @author scaf_xs
 * @ClassName: SysUserMapper
 * @Description: 用户服务mapper
 * @date 2019/2/26 15:30
 */
@Mapper
public interface SysUserMapper {
    /**
     *通过用户名和密码获取用户
     * @param map
     * @return SysUser
     */
    SysUser getUserByMap(Map<String,String> map);

    /**
     * 获取到所有用户
     * @param map
     * @return
     */
    List<SysUser> getAllUsers(Map<String,Object> map);

    /**
     * 获取用户的总数
     * @return
     */
    Integer getUsersCount();

    /**
     * 创建用户
     * @param sysUser
     */
    void create(SysUser sysUser);

    /**
     * 删除用户的角色
     * @param userId
     */
    void deleteUserRoles(String userId);

    /**
     * 用户分配角色
     * @param id
     * @param userId
     * @param roleId
     */
    void assortUserRole(@Param("id") String id, @Param("userId") String userId, @Param("roleId") String roleId);
}
