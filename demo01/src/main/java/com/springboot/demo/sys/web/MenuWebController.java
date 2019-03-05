package com.springboot.demo.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author scaf_xs
 * @ClassName: MenuWebController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/3/4 10:18
 */
@Controller
@RequestMapping("/sys")
public class MenuWebController {

    @RequestMapping("/user-list")
    public String userList(){
        return "/sys/user-list";
    }
}
