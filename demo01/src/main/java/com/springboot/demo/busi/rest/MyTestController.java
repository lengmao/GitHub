package com.springboot.demo.busi.rest;

import com.springboot.demo.busi.entity.MyTestEntity;
import com.springboot.demo.busi.service.MyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: MyTestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/22 16:24
 */
@RestController
@RequestMapping("/test")
public class MyTestController {

    @Autowired
    MyTestService myTestService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<MyTestEntity> getList(){
       return myTestService.getList();
    }
}
