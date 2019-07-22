package com.ngt.controller;

import com.ngt.dao.UserDao;
import com.ngt.entity.User;
import com.ngt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author scaf_xs
 * @ClassName: HomeController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/17 17:49
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String test(){
        return "index";
    }


    @RequestMapping("/getById")
    @ResponseBody
    public User getUserById(){
       return userService.getById(1);
    }


}
