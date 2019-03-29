package com.springboot.demo.sys.rest;

import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.commom.util.PageBean;
import com.springboot.demo.commom.util.Result;
import com.springboot.demo.sys.entity.SysUser;
import com.springboot.demo.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: SysUserRestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/3/7 15:28
 */
@RestController
@RequestMapping("/sys")
public class SysUserRestController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public PageBean pager(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> userMap = sysUserService.getAllUsers(pageNumber, pageSize);
        int count = (int) userMap.get("count");
        List<SysUser> list = (List<SysUser>) userMap.get("sysUsers");
        PageBean pageBean = new PageBean(pageNumber, pageSize, count, list);
        return pageBean;
    }

    @RequestMapping(value = "/sysUser", method = RequestMethod.POST)
    public Object create(@RequestParam() String beanJson) {
        Result<?> result = new Result();
        try {
            sysUserService.create(beanJson);
            result.setOk(true);
            result.setMsg("创建成功!");
        } catch (BusiException e) {
            result.setOk(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/assortUserRole/{userId}")
    public Object assortRole(@PathVariable String userId,@RequestParam String roleIds){
        Result<Object> res=new Result<>();
        try{
            sysUserService.deleteUserRoles(userId);
            String[] roleIdStr= roleIds.split(",");
            sysUserService.assortUserRole(userId,roleIdStr);
            res.setOk(true);
            res.setMsg("操作成功!");
        }catch (BusiException e){
            res.setOk(false);
            res.setMsg("操作失败!");
        }
        return res;
    }
}
