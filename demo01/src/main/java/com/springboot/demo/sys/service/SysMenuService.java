package com.springboot.demo.sys.service;

import com.springboot.demo.commom.exception.BusiException;
import com.springboot.demo.sys.entity.SysMenu;
import com.springboot.demo.sys.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: SysMenuService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/28 10:39
 */
@Service
public class SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    public List<SysMenu> getAllSysMenus(){
        List<SysMenu> sysMenus;
        try {
            sysMenus=sysMenuMapper.getAllSysMenus();
        }catch (BusiException e){
            throw new BusiException(e.getMessage());
        }
       return sysMenus;
    }
}
