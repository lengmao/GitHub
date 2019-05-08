package com.lm.pag.mapper;


import com.lm.pag.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author scaf_xs
 * @ClassName: UserMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/15 16:29
 */

/**
 * 继承通用Mapper获取CURD方法
 */
public interface UserMapper extends Mapper<User> {

}
