package com.xinke.management;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan("com.xinke.management.dao")
@EnableCaching
public class ManagementBootStartApplication
{
    private static Logger logger = LoggerFactory.getLogger(ManagementBootStartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ManagementBootStartApplication.class, args);
    }

}
