package com.frame.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by scaf_xs on 2017/9/28.
 */

@Controller
@RequestMapping("")
public class WebController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String getWeb(){
        return "index";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String getEdit(){
        return "edit";
    }

    @RequestMapping(value = "/entityEdit",method = RequestMethod.GET)
    public String getEntityEdit(){
        return "entity_edit";
    }

    @RequestMapping(value = "/demoTest",method = RequestMethod.GET)
    public String getDemo_test(){
        return "demo/demo_form";
    }

    @RequestMapping(value = "/fileOpera",method = RequestMethod.GET)
    public String fileOPera(){
        return "file";
    }

}
