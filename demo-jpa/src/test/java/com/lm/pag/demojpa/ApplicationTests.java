package com.lm.pag.demojpa;

import com.lm.pag.demojpa.entity.Role;
import com.lm.pag.demojpa.entity.User;
import com.lm.pag.demojpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void testSave() {
        User user = new User(1L, "consumer01", "111111", "eeeee");
        userService.save(user);
        userService.findById(1L);
        userService.count();
        userService.delete(user);
        userService.deleteById(1L);
        userService.findAll();
    }

    @Test
    public void testUpdate() {
//        User user = new User(1L, "consumer02", "222222", "eeeee");
//        userService.save(user);
//        userService.findUserByUserName("consumer02").getPassword();
//        userService.modifyById("consumer001", 1L);
//        userService.deleteByUserId(2L);
        List<Role> roles=userService.findRoleByUser(1L);
        for(Role role:roles){
            System.out.println(role.getRoleId()+" / "+role.getRoleName()+" / "+role.getRoleDiscription());
        }
    }

    @Test
    public void testPage() {
        int pageNumber = 1, pageSize = 5;
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
        userService.findAll(pageable);
    }

}
