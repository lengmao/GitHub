package com.springboot.demo.sys.rest;

import com.springboot.demo.sys.dto.User;
import com.springboot.demo.sys.entity.SysUser;
import com.springboot.demo.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: UserLoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/14 15:00
 */
@RestController
public class UserLoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/toLogin",method = RequestMethod.POST)
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map= new HashMap<>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        if(!userName.trim().equals("")&&!password.trim().equals("")){
            User user=new User(userName,password);
            request.getSession().setAttribute("user",user);
            map.put("result","1");
        }else {
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping(value = "/submitLogin",method = RequestMethod.POST)
    public Map<String,Object> submitLogin(HttpServletRequest request
//            @RequestParam String name,
//            @RequestParam String password,
//            @RequestParam boolean rememberMe
        ){
        Map<String,Object> res=new LinkedHashMap<>();
        try{
            UsernamePasswordToken token=new UsernamePasswordToken(
                    request.getParameter("name"),
                    request.getParameter("password"),
                    request.getParameter("rememberMe"));
            SecurityUtils.getSubject().login(token);
            res.put("status",200);
            res.put("msg","登录成功");
        }catch (Exception e){
            res.put("status",500);
            res.put("msg",e.getMessage());
        }
        return res;
    }
}
