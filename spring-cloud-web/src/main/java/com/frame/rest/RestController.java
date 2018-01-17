package com.frame.rest;/**
 * Created by scaf_xs on 2017/12/16.
 */

import com.frame.config.TableResultResponse;
import com.frame.entity.Entity;
import com.frame.entity.User;
import com.frame.mapper.EntityMapper;
import com.frame.mapper.TableMapper;
import com.frame.mapper.UserMapper;
import com.frame.service.TableService;
import com.frame.util.JSONUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author scaf_xs
 * @ClassName: RestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/12/16 16:08
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest")
public class RestController extends BaseController {
    private  String schema="maintenance";
    @Autowired
    TableService tableService;

    @Autowired
    TableMapper tableMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    EntityMapper entityMapper;

//    @RequestMapping(value = "/pageList",method = RequestMethod.GET)
//    public Object getTableList(String schema01,
//                               @RequestParam(defaultValue = "10") Integer limit,
//                               @RequestParam(defaultValue = "1")Integer offset,
//                               String tableName){
//        schema01= schema;
//
////            Integer limit=10;
////            Integer offset=1;
////            String  tableName="test_users";
//
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++==");
//        System.out.println(schema01+"--"+limit+"--"+offset+"--"+tableName);
//          List tables=tableMapper.getTableList(schema01,limit,offset,tableName);
//          int total=tableMapper.getTableTotal(schema);
//
//
//          return new TableResultResponse<List>(total,tables);
//    }


    @RequestMapping(value = "/num",method = RequestMethod.GET)
    public int getTableTotal(String tableName){

        return tableMapper.getTableTotal(schema);
    }

    @RequestMapping(value = "/pageEntitys",method = RequestMethod.GET)
    public Object getAllEntity(@RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(defaultValue = "1")Integer offset,
                                        String entityName){

        int count=entityMapper.getCountEntity();
        List entitys= entityMapper.getAllEntity(limit,offset,entityName);
        return new TableResultResponse<List>(count,entitys);
    }

    @RequestMapping(value = "/addEntity",method = RequestMethod.POST)
    public boolean addEntity(@RequestBody String data){
        Map<String,String> map=JSONUtil.toMapString(data);
        Integer parent_id= (Integer.parseInt(map.get("parent_id"))) ;
        String entity_code= map.get("entity_code");
        String entity_name= map.get("entity_name");

        return entityMapper.insertEntity(parent_id,entity_code,entity_name);
    }

    @RequestMapping(value = "/getEntityById",method = RequestMethod.POST)
    public Entity  getEntityById(@RequestBody String entity_id){
        Map<String,String> map=JSONUtil.toMapString(entity_id);
        int id=Integer.parseInt(map.get("id"));
       return entityMapper.getEntityById(id);
    }

    @RequestMapping(value = "/editEntity",method = RequestMethod.POST)
    public boolean editEntity(@RequestBody String data){
        Map<String,String> map=JSONUtil.toMapString(data);
        int entity_id=(Integer.parseInt(map.get("entity_id"))) ;
        int parent_id= (Integer.parseInt(map.get("parent_id"))) ;
        String entity_code= map.get("entity_code");
        String entity_name= map.get("entity_name");
        return entityMapper.updateEntity(entity_id,parent_id,entity_code,entity_name);
    }


    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> getAlluser(@RequestParam(defaultValue = "10") int limit,
                                 @RequestParam(defaultValue = "1") int offset,
                                 String name){


        return userMapper.getAll();
    }

    @RequestMapping(value = "/getUserById")
    public User getUserById(@Param("id") Long id){
        return  userMapper.getOne(id);
    }


}
