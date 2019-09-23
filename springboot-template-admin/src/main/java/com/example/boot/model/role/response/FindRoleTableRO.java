package com.example.boot.model.role.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/17 17:33
 */
@Data
public class FindRoleTableRO {

    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private Timestamp updateTime;
}
