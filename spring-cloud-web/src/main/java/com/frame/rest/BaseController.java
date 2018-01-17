package com.frame.rest;/**
 * Created by scaf_xs on 2017/12/18.
 */

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author scaf_xs
 * @ClassName: BaseController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/12/18 19:24
 */

public class BaseController {
    @Autowired
    private HttpServletRequest request;
    public String getCurrentUserName(){
//        String authorization = request.getHeader("Authorization");
//        return new String(Base64Utils.decodeFromString(authorization));
        return (String)request.getSession().getAttribute("Authorization");
    }
}