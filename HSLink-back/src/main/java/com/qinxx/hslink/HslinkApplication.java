package com.qinxx.hslink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.qinxx.hslink.dao"})
@SpringBootApplication
public class HslinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HslinkApplication.class, args);
       }

}
