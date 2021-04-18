package com.study.qw;

import com.study.qw.beans.Klass;
import com.study.qw.beans.School;
import com.study.qw.beans.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class StartApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringContextOne.xml");
        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());
        student123.printInfo();
        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());
        student100.printInfo();
        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);
        class1.printStudents();

        School school1 = context.getBean(School.class);
        System.out.println(school1);

        school1.printInfo();

    }

}
