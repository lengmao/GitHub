package com.frame.rest;

import com.frame.service.TableService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: TableController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/12/28 15:19
 */
@RestController
@RequestMapping("/tableInfo")
public class TableController {

    @Autowired
    TableService tableService;

    @RequestMapping(value = "/tables",method = RequestMethod.GET)
    public void getTables(){
        Map<String,String> map= Maps.newHashMap();
        map.put("dbDriver","com.mysql.jdbc.Driver");
        map.put("dbUrl","jdbc:mysql://127.0.0.1:3306/maintenance");
        map.put("username","root");
        map.put("password","admin");
        System.out.println("=======================================");
        System.out.println(tableService.getTableList(map));
    }
}
