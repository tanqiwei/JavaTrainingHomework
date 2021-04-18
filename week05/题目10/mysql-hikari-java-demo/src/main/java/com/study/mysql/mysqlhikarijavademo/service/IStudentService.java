package com.study.mysql.mysqlhikarijavademo.service;


import com.study.mysql.mysqlhikarijavademo.entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tqw
 */
public interface IStudentService {


    int create(Student student) throws SQLException;

    int update(String serialNo,String phone) throws SQLException;

    int delete(String serialNo) throws SQLException;

    Student searchBySerialNo(String serialNo) throws SQLException;

    int[] batchCreate(List<Student> studentList) throws SQLException;

    int[] batchDelete(List<String> searchNos) throws SQLException;
}
