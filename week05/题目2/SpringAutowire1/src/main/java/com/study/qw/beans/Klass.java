package com.study.qw.beans;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Klass {

    private final List<Student> students;

    public void printStudents() {
        System.out.printf("this class sampleName  is [%s].", this.getClass().getSimpleName());
        System.out.println("current klass 's student:" + this.getStudents());
    }

}
