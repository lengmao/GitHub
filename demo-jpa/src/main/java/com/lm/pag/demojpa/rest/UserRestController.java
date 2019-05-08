package com.lm.pag.demojpa.rest;

import com.lm.pag.demojpa.entity.User;
import com.lm.pag.demojpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author scaf_xs
 * @ClassName: UserRestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/7 14:14
 */
@RestController
@RequestMapping("/")
public class UserRestController {

    @RequestMapping(value ="create",method = RequestMethod.POST)
    public void create(){

    }
}
