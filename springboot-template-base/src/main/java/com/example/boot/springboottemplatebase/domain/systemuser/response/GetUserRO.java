package com.example.boot.springboottemplatebase.domain.systemuser.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/3 0:13
 */
@Data
public class GetUserRO {

    private String username;
    private String roleName;
    private String phone;
    private String nickname;
    private String avatar;
    private String description;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastLoginTime;
}
