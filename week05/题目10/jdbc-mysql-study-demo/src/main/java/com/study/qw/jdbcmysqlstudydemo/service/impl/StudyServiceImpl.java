package com.study.qw.jdbcmysqlstudydemo.service.impl;

import com.study.qw.jdbcmysqlstudydemo.config.MysqlConfig;
import com.study.qw.jdbcmysqlstudydemo.entity.Student;
import com.study.qw.jdbcmysqlstudydemo.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author tqw
 */
@Service
@Slf4j
public class StudyServiceImpl implements IStudentService {

    @Autowired
    private MysqlConfig config;


    @Override
    public int create(Student student) {
        String sql = "insert into student(name,serialNo,phone,address) values (?,?,?,?)";

        try (Connection connection = config.createConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSerialNo());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getAddress());
            return stmt.executeUpdate();
        } catch (Throwable e) {
            log.error("not to insert a student", e);
            return 0;
        }
    }

    @Override
    public int update(String serialNo, String phone) {
        String sql = "update student set phone=? where serialNo=?";
        try (Connection connection = config.createConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            stmt.setString(2, serialNo);
            return stmt.executeUpdate();
        } catch (Throwable e) {
            log.error("not to update a student", e);
            return 0;
        }
    }

    @Override
    public int delete(String serialNo) {
        String sql = "DELETE FROM student WHERE serialNo = ?";
        try (Connection connection = config.createConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, serialNo);
            return stmt.executeUpdate();
        } catch (Throwable e) {
            log.error("not to delete a student", e);
            return 0;
        }
    }


    @Override
    public Student searchBySerialNo(String serialNo) {
        String sql = "SELECT * FROM student where serialNo=?";
        try (Connection connection = config.createConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, serialNo);
            try (ResultSet resultSet = stmt.executeQuery()) {

                if (resultSet.next()) {
                    //(name,serialNo,phone,address)
                    String name = resultSet.getString("name");
                    String currentSerialNo = resultSet.getString("serialNo");
                    String phone = resultSet.getString("phone");
                    String address = resultSet.getString("address");
                    return Student.builder().serialNo(serialNo)
                            .address(address).name(name).phone(phone).build();
                }


            }


        } catch (Throwable e) {
            log.error("not to delete a student", e);
        }

        return null;
    }
}
