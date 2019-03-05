package com.springboot.demo.sys.mapper;

import com.springboot.demo.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
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
     * 获取所有菜单列表
     * @return
     */
    List<SysMenu> getAllSysMenus();
}
