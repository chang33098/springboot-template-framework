package com.example.boot.springboottemplatebase.domain.systempermission.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/11 13:16
 */
@Data
public class FindPermissionTableRO {

    private Long id;
    private String name;
    private String code;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}
