package com.study.mysql.mysqlhikarijavademo;

import com.study.mysql.mysqlhikarijavademo.entity.Student;
import com.study.mysql.mysqlhikarijavademo.service.IStudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest(classes = MysqlHikariJavaDemoApplication.class)
class MysqlHikariJavaDemoApplicationTests {

    @Autowired
    private IStudentService studentService;

    private static PodamFactory podamFactory = new PodamFactoryImpl();

    @Test
    void testJdbc() throws SQLException {
        Student student = podamFactory.manufacturePojo(Student.class);
        Assertions.assertEquals(1, studentService.create(student));
        String newPhone = podamFactory.manufacturePojo(String.class);
        Assertions.assertEquals(1, studentService.update(student.getSerialNo(), newPhone));
        Assertions.assertEquals(1, studentService.delete(student.getSerialNo()));
        Assertions.assertNull(studentService.searchBySerialNo(student.getSerialNo()));
        Student student2 = podamFactory.manufacturePojo(Student.class);
        Assertions.assertEquals(1, studentService.create(student2));
        Student student3 = studentService.searchBySerialNo(student2.getSerialNo());
        Assertions.assertNotNull(student3);
        Assertions.assertEquals(student2.getSerialNo(), student3.getSerialNo());
        Assertions.assertEquals(1, studentService.delete(student3.getSerialNo()));

        /*
         * batch service
         */
        List<Student> students = podamFactory.manufacturePojo(ArrayList.class, Student.class);
        Assertions.assertTrue(Arrays.stream(studentService.batchCreate(students)).allMatch(result -> Objects.equals(1, result)));
        List<String> searchNos = students.stream().map(Student::getSerialNo).collect(Collectors.toList());
        Assertions.assertTrue(Arrays.stream(studentService.batchDelete(searchNos)).allMatch(result -> Objects.equals(1, result)));



    }
}
