package com.as;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//@ServletComponentScan("com.as.MyAspect")
public class AsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsApplication.class, args);
    }

}
