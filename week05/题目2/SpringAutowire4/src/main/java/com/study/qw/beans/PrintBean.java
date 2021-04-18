package com.study.qw.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author tqw
 */
@Data
@NoArgsConstructor
@Component
public class PrintBean implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        Student student123 = (Student) applicationContext.getBean("student123");
        System.out.println(student123.toString());
        student123.printInfo();
        Student student100 = (Student) applicationContext.getBean("student100");
        System.out.println(student100.toString());
        student100.printInfo();
        Klass class1 = applicationContext.getBean(Klass.class);
        System.out.println(class1);
        class1.printStudents();

        School school1 = applicationContext.getBean(School.class);
        System.out.println(school1);

        school1.printInfo();
    }
}
