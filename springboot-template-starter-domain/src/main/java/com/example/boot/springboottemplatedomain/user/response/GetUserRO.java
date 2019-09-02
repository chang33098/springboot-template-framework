package com.example.boot.springboottemplatedomain.user.response;

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
    private String phone;
    private String nickname;
    private String avatar;
    private String description;
    private Integer status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp lastLoginTime;
}
