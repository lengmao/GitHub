package com.springboot.demo.busi.service;

import com.springboot.demo.busi.entity.MyTestEntity;
import com.springboot.demo.busi.mapper.MyTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: MyTestService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/22 16:23
 */
@Service
public class MyTestService {

    @Autowired
    MyTestMapper myTestMapper;

    public List<MyTestEntity> getList(){
        return myTestMapper.getList();
    }
}
