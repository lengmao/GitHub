package com.springboot.demo.sys.rest;

import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.sys.entity.SysMenu;
import com.springboot.demo.sys.service.SysMenuService;
import com.springboot.demo.util.TreeListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: SysMenuRestContorller
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/28 10:50
 */
@RestController
public class SysMenuRestController {
    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public List<SysMenu> getAllSysMenus(){
        List<SysMenu> menuTree;
        try {
            List<SysMenu> menuList=sysMenuService.getAllSysMenus();
            menuTree=TreeListUtil.listTree(menuList);
        }catch (BusiException e){
            throw new BusiException(e.getMessage());
        }
        return menuTree;
    }
}
