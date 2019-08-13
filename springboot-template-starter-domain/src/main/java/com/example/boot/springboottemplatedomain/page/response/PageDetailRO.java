package com.example.boot.springboottemplatedomain.page.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 15:20
 */
@Data
public class PageDetailRO {

    private Long id;
    private String code;
    private String name;
    private String url;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}
