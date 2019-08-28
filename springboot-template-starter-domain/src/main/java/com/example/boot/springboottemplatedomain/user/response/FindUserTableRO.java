package com.example.boot.springboottemplatedomain.user.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/28 7:09
 */
@Data
public class FindUserTableRO {

    private Long id;
    private String username;
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

    public static List<FindUserTableRO> create(List<SystemUser> users) {
        List<FindUserTableRO> userROS = new ArrayList<>(users.size());
        users.forEach(user -> {
            FindUserTableRO userRO = new FindUserTableRO();
            BeanUtil.copyProperties(user, userRO);

            userROS.add(userRO);
        });

        return userROS;
    }
}
