package com.springboot.demo.sys.mapper;

import com.springboot.demo.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

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
}
