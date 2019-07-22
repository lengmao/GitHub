package com.ngt.dao;

import com.ngt.entity.User;


/**
 * @author scaf_xs
 * @ClassName: UserDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/17 23:06
 */

public interface UserDao {

    User selectById(int id);
}
