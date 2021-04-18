package com.study.qw.jdbcmysqlstudydemo;

import com.study.qw.jdbcmysqlstudydemo.entity.Student;
import com.study.qw.jdbcmysqlstudydemo.service.IStudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest(classes = JdbcMysqlStudyDemoApplication.class)
class JdbcMysqlStudyDemoApplicationTests {

    @Autowired
    private IStudentService studentService;

    private static PodamFactory podamFactory = new PodamFactoryImpl();

    @Test
    void testJdbc() {
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
    }

}
