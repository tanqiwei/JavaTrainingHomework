package com.study.qw.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
@AllArgsConstructor
public class School {

    private final Klass class1;

    @Qualifier(value = "student100")
    private final Student student;

    public void printInfo() {
        class1.printStudents();
    }

}
