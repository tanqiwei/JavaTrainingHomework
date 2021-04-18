package com.study.qw.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tqw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private int serialNo;
    private String name;

    public void printInfo() {
        System.out.printf("current student name is [%s] and serialNo is [%s].", name, serialNo);
    }

}
