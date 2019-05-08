package com.lm.pag.common;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: BaseService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/15 16:36
 */

public class BaseService<T> {

    //泛型装配
    @Autowired
    private Mapper<T> mapper;


    public List<T> list() {
        List<T> list=new ArrayList<>();
        try {
            list=mapper.selectAll();
        } catch (Exception e) {

        }
        return list;
    }

    /**
     * 一下的每一个方法传值都是一个实体类，可根据自己的业务进行判断
     * 以 get() 方法为例（做了一个简单的判断）
     * @param entity
     * @return
     */
    public T get(T entity) {

        try {
            if (null != entity && !"".equals(entity)) {
                return mapper.selectOne(entity);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public int update(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public int save(T entity) {
        return mapper.insertSelective(entity);
    }

    public int delete(T entity) {
        return mapper.deleteByPrimaryKey(entity);
    }
}
