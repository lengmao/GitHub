package com.springboot.demo;


import com.springboot.demo.busi.service.MyTestService;
import com.springboot.demo.commom.config.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
    DataSourceConfig applicationConfig;

	@Autowired
	MyTestService myTestService;

	@Test
	public void getDataSource() {

		System.out.println(myTestService.getList());
	}
}

