package com.lm.pag.rest;

import com.lm.pag.entity.User;
import com.lm.pag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: UserRestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/15 16:39
 */

@RestController
@RequestMapping("/user")
public class UserRestController {

    /**
     * 如果之前的没有在UserService类加@Service注解，这里将获取不到UserService
     */
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    /**
     * 这里每一个方法传递的是一个实体，而接口调用一般不这么传值，
     * 需要将传入的参数用set方法放进new出来的实体即可
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    public User get(@PathVariable String id) {
        User user=new User();
        user.setUserId(id);
        return userService.get(user);
    }

    @RequestMapping("update")
    public int update(User user) {
        return userService.update(user);
    }

    @RequestMapping("save")
    public int save(User user) {
        return userService.save(user);
    }

    @RequestMapping("delete")
    public int delete(User user) {
        return userService.delete(user);
    }
}
