package com.springboot.demo.sys.rest;

import com.springboot.demo.commom.config.MySessionManage;
import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.commom.util.Result;
import com.springboot.demo.sys.entity.SysMenu;
import com.springboot.demo.sys.entity.SysUser;
import com.springboot.demo.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: SysMenuRestContorller
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/28 10:50
 */
@RestController
@RequestMapping("/sys")
public class SysMenuRestController {
    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping(value = "/userMenu", method = RequestMethod.GET)
    public List<SysMenu> getUserMenus() {
        List<SysMenu> menuList;
        try {
            SysUser sysUser = MySessionManage.getCurrentUser();
            menuList = sysMenuService.getMenusByUser(sysUser.getId());
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return menuList;
    }

    @RequestMapping(value = "/sysMenu", method = RequestMethod.GET)
    public List<SysMenu> getAllMenu() {
        List<SysMenu> menuList;
        try {
            menuList = sysMenuService.getAllMenu();
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return menuList;
    }

    @RequestMapping(value = "/sysMenu", method = RequestMethod.POST)
    public Object create(@RequestParam String beanJson) {
        Result<?> res = new Result<>();
        try {
            sysMenuService.create(beanJson);
            res.setOk(true);
            res.setMsg("创建成功!");
        } catch (BusiException e) {
            res.setOk(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "/sysMenu/{code}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String code) {
        Result<Object> res = new Result<>();
        try {
            sysMenuService.delete(code);
            res.setMsg("删除成功!");
            res.setOk(true);
        } catch (BusiException e) {
            res.setOk(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }

}
