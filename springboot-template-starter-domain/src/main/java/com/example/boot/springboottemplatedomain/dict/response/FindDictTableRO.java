package com.example.boot.springboottemplatedomain.dict.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:23
 */
@Data
public class FindDictTableRO {

    private Long id;
    private String type;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
