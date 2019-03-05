package com.springboot.demo.sys.service;

import com.springboot.demo.commom.BusiException;
import com.springboot.demo.sys.entity.SysUser;
import com.springboot.demo.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: UserService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/26 14:31
 */
@Service
public class SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    public SysUser getUserByMap(Map<String, String> map) {
        SysUser sysUser=new SysUser();
        try{
            sysUser=sysUserMapper.getUserByMap(map);
        }catch (BusiException e){
            throw new BusiException(e.getMessage());
        }
        return sysUser;
    }
}
