package org.study.qw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.study.qw.beans.Student;

/**
 * @author tqw
 */
@Configuration
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
