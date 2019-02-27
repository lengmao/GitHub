package com.springboot.demo;

import com.alibaba.fastjson.JSON;
import com.springboot.demo.config.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
    DataSourceConfig applicationConfig;

	@Test
	public void getDataSource() {
		System.out.println("-----------------------------------------------");
		System.out.println(applicationConfig.getDataSource());

		JdbcTemplate jdbcTemplate=new JdbcTemplate(applicationConfig.getDataSource());
		List<?> list= jdbcTemplate.queryForList("SELECT * FROM city");
		System.out.println("================>>>>>>");
		System.out.println(JSON.toJSONString(list));
	}
}

