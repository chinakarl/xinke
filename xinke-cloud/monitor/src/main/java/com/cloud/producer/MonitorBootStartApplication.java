package com.cloud.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class MonitorBootStartApplication
{
    private static Logger logger = LoggerFactory.getLogger(MonitorBootStartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MonitorBootStartApplication.class, args);
    }

}
