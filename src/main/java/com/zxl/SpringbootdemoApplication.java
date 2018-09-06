package com.zxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 开启spring的组件扫描和Spring boot的自动配置
 */
//@MapperScan("com.zxl.dao")	在启动类中添加对mapper包扫描@MapperScan,或者直接在Mapper类上面添加注解@Mapper
@SpringBootApplication
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}
}
