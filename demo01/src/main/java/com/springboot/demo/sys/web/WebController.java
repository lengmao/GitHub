package com.springboot.demo.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author scaf_xs
 * @ClassName: WebController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/14 10:58
 */
@Controller
@RequestMapping("")
public class WebController {

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/403")
    public String unauthorized() {
        return "/error/403";
    }

    @RequestMapping("/404")
    public String nourl() {
        return "/error/404";
    }
}
