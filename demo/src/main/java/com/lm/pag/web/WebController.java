package com.lm.pag.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author scaf_xs
 * @ClassName: WebController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/16 16:31
 */
@Controller
public class WebController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toMyFirst(){
        return "/myFirst";
    }
}
