package com.frame.mapper;

/**
 * Created by scaf_xs on 2017/12/18.
 */
import com.frame.entity.User;

import java.util.List;



public interface UserMapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}
