package com.study.qw.starter.config;

import com.study.qw.starter.beans.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author tqw
 */
@Configuration
@ComponentScan(basePackages = "com.study.qw.starter.beans")
public class SpringConfig {
    @Bean
    public Student student100() {
        return Student.builder().serialNo(100).name("student100").build();
    }

    @Bean
    public Student student123() {
        return Student.builder().serialNo(123).name("student123").build();
    }


}
