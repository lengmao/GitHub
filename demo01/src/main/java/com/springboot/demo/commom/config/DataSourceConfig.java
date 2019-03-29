package com.springboot.demo.commom.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

/**
 * @author scaf_xs
 * @ClassName: ApplicationConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/12 11:55
 */
@Configuration
@ComponentScan
public class DataSourceConfig {

    @Autowired
    private Environment evn;

    @Bean(destroyMethod = "close")
    public DataSource getDataSource() {

        /* 基础信息配置 */
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(evn.getProperty("spring.datasource.url"));
        dataSource.setUsername(evn.getProperty("spring.datasource.username"));
        dataSource.setPassword(evn.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(evn.getProperty("spring.datasource.driver-class-name"));

        /* 初始化时建立物理连接的个数 */
        dataSource.setInitialSize(2);
        /* 最大连接池数量 */
        dataSource.setMaxActive(20);

        /* 最小连接池数量 */
        dataSource.setMinIdle(0);

        /* 获取连接时最大等待时间，单位毫秒 */
        dataSource.setMaxWait(60000);

        /* 用来检测连接是否有效的sql */
        dataSource.setValidationQuery("SELECT 1");

        /* 申请连接时执行validationQuery检测连接是否有效 */
        dataSource.setTestOnBorrow(false);

        /* 建议配置为true，不影响性能，并且保证安全性 */
        dataSource.setTestWhileIdle(true);

        /* 是否缓存preparedStatement，也就是PSCache */
        dataSource.setPoolPreparedStatements(false);

        return dataSource;
    }
}
