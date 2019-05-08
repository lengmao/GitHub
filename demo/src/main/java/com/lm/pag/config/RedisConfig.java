package com.lm.pag.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;


/**
 * @author scaf_xs
 * @ClassName: RedisConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/24 16:31
 */

/**
 * 使用@Configuration注解申明配置类
 * 使用@EnableCaching注解开启缓存
 * 继承CachingConfigurerSupport父类
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object object : objects) {
                    sb.append(object.toString());
                }
                return sb.toString();
            }
        };
    }
}
