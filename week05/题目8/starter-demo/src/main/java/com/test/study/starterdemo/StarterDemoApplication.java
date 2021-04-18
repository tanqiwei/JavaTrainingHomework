package com.test.study.starterdemo;

import com.study.qw.starter.config.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringConfig.class)
public class StarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterDemoApplication.class, args);
    }

}
