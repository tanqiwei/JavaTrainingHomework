package com.study.mysql.mysqlhikarijavademo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tqw
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Student {

    private String name;

    private String serialNo;

    private String phone;

    private String address;


}
