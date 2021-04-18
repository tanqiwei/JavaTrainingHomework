package com.study.mysql.mysqlhikarijavademo.service.impl;

import com.study.mysql.mysqlhikarijavademo.entity.Student;
import com.study.mysql.mysqlhikarijavademo.service.IStudentService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author tqw
 */
@Service
@Slf4j
public class StudyServiceImpl implements IStudentService {


    @Autowired
    private HikariDataSource hikariDataSource;

    @Override
    @Transactional
    public int create(Student student) throws SQLException {
        String sql = "insert into student(name,serialNo,phone,address) values (?,?,?,?)";
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSerialNo());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getAddress());
            return stmt.executeUpdate();
        }
    }

    @Override
    @Transactional
    public int update(String serialNo, String phone) throws SQLException {
        String sql = "update student set phone=? where serialNo=?";
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            stmt.setString(2, serialNo);
            return stmt.executeUpdate();
        }
    }

    @Override
    @Transactional
    public int delete(String serialNo) throws SQLException {
        String sql = "DELETE FROM student WHERE serialNo = ?";
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, serialNo);
            return stmt.executeUpdate();
        }
    }


    @Override
    @Transactional
    public Student searchBySerialNo(String serialNo) throws SQLException {
        String sql = "SELECT * FROM student where serialNo=?";
        try (Connection connection = hikariDataSource.getConnection();
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
        }

        return null;
    }

    @Override
    public int[] batchCreate(List<Student> studentList) throws SQLException {
        String sql = "insert into student(name,serialNo,phone,address) values (?,?,?,?)";
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Student student : studentList) {
                stmt.setString(1, student.getName());
                stmt.setString(2, student.getSerialNo());
                stmt.setString(3, student.getPhone());
                stmt.setString(4, student.getAddress());
                stmt.addBatch();
            }
            return stmt.executeBatch();
        }
    }

    @Override
    public int[] batchDelete(List<String> searchNos) throws SQLException {
        String sql = "delete from student where serialNo=?";
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String serialNo : searchNos) {
                stmt.setString(1, serialNo);
                stmt.addBatch();
            }
            return stmt.executeBatch();
        }
    }
}
