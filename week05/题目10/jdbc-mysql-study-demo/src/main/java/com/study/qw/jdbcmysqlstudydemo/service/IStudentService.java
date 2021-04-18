package com.study.qw.jdbcmysqlstudydemo.service;

import com.study.qw.jdbcmysqlstudydemo.entity.Student;

import java.util.List;

/**
 * @author tqw
 */
public interface IStudentService {


    int create(Student student);

    int update(String serialNo,String phone);

    int delete(String serialNo);

    Student searchBySerialNo(String serialNo);

}
