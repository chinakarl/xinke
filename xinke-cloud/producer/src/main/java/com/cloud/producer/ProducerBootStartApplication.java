package com.cloud.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerBootStartApplication
{
    private static Logger logger = LoggerFactory.getLogger(ProducerBootStartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProducerBootStartApplication.class, args);
    }

}
