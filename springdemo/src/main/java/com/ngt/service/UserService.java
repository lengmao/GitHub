package com.ngt.service;

import com.ngt.dao.UserDao;
import com.ngt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author scaf_xs
 * @ClassName: UserService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/18 9:24
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.selectById(id);
    }
}
