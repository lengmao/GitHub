package com.springboot.demo.busi.mapper;

import com.springboot.demo.busi.entity.MyTestEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: MyTestMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/22 16:29
 */
@Mapper
public interface MyTestMapper {

    List<MyTestEntity> getList();
}
