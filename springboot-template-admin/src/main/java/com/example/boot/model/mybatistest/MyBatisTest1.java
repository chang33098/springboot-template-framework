package com.example.boot.model.mybatistest;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/9 10:55
 */
@Data
public class MyBatisTest1 {

    private Long id;
    private String stringColumn;
    private Integer intColumn;
    private Double doubleColumn;
    private Boolean byteColumn;
    private Date dateColumn;
    private Timestamp timestampColumn;
}
