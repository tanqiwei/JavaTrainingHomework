package com.study.qw.starter.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Klass {

    @Autowired
    private List<Student> students;

    public void printStudents() {
        System.out.printf("this class sampleName  is [%s].", this.getClass().getSimpleName());
        System.out.println("current klass 's student:" + this.getStudents());
    }

}
