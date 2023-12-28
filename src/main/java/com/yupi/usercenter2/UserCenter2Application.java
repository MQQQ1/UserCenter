package com.yupi.usercenter2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yupi.usercenter2.Mapper")
public class UserCenter2Application {

    public static void main(String[] args) {
        SpringApplication.run(UserCenter2Application.class, args);
    }

}
