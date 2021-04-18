package org.study.qw.beans;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class School {
    @Autowired
    private Klass class1;

    @Autowired
    @Qualifier(value = "student123")
    private Student student;

    public void printInfo() {
        class1.printStudents();
    }

}
