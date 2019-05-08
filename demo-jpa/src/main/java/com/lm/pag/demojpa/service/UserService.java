package com.lm.pag.demojpa.service;

import com.lm.pag.demojpa.entity.Role;
import com.lm.pag.demojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: UserService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/7 11:57
 */
@Repository
public interface UserService extends JpaRepository<User, Long> {

    @Transactional(timeout = 10)
    @Query("select u from User u where u.username=:username")
    User findUserByUserName(@Param("username") String username);

    @Transactional(timeout = 20)
    @Modifying
    @Query("update User u set u.username=:username where u.userId=:id")
    int modifyById(@Param("username") String username, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from User u where u.userId=:userId")
    void deleteByUserId(@Param("userId") Long userId);


    @Transactional(timeout = 10)
    @Query("select r from Role r ,User u,UserRole ur where r.roleId=ur.rId and ur.uId=u.userId and u.userId=:userId")
    List<Role> findRoleByUser(@Param("userId") Long userId);
}
