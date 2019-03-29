package com.springboot.demo.sys.rest;

import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.commom.util.PageBean;
import com.springboot.demo.commom.util.Result;
import com.springboot.demo.sys.entity.SysMenu;
import com.springboot.demo.sys.entity.SysRole;
import com.springboot.demo.sys.service.SysMenuService;
import com.springboot.demo.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author scaf_xs
 * @ClassName: SysRoleRestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/3/21 16:12
 */
@RestController
@RequestMapping(value = "/sys")
public class SysRoleRestController {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping(value = "/rolePage", method = RequestMethod.GET)
    public PageBean getAllRoles(@RequestParam(defaultValue = "1") int pageNumber,
                                @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> map = PageBean.pageMap(pageNumber, pageSize);
        Map<String, Object> roleMap = sysRoleService.getAllRoles(map);
        int count = (int) roleMap.get("count");
        List<SysRole> list = (List<SysRole>) roleMap.get("sysRoles");
        PageBean pageBean = new PageBean(pageNumber, pageSize, count, list);
        return pageBean;
    }


    @RequestMapping(value = "/roleMenu/{id}", method = RequestMethod.GET)
    public Map<String, Object> getRoleMenu(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>(10);
        try {
            List<SysMenu> menus = sysMenuService.getAllMenu();
            List<SysMenu> roleMenus = sysRoleService.getRoleMenu(id);
            map.put("menus", menus);
            map.put("roleMenus", roleMenus);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/userRole/{userId}", method = RequestMethod.GET)
    public List<SysRole> getUserRole(@PathVariable String userId) {
        List<SysRole> list = null;
        try {
            list = sysRoleService.getUserRole(userId);
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
        return list;
    }

    @RequestMapping(value = "/sysRole", method = RequestMethod.POST)
    public Object create(@RequestParam String beanJson) {
        Result<Object> res = new Result<>();
        try {
            sysRoleService.create(beanJson);
            res.setMsg("创建成功!");
            res.setOk(true);
        } catch (BusiException e) {
            res.setOk(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "/updatePower/{id}", method = RequestMethod.POST)
    public Object updatePower(@PathVariable String id, @RequestParam String menuIds) {
        Result<Object> res = new Result<>();
        try {
            sysRoleService.deleteRoleMenus(id);
            String[] menuIdStr = menuIds.split(",");
            sysRoleService.updatePower(id, menuIdStr);
            res.setMsg("修改成功");
            res.setOk(true);
        } catch (BusiException e) {
            res.setOk(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }
}
