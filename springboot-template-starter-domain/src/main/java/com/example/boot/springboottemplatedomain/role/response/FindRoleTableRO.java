package com.example.boot.springboottemplatedomain.role.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public static List<FindRoleTableRO> create(List<SystemRole> roles) {
        List<FindRoleTableRO> roleROS = new ArrayList<>();
        roles.forEach(role -> {
            FindRoleTableRO roleRO = new FindRoleTableRO();
            BeanUtil.copyProperties(role, roleRO);

            roleROS.add(roleRO);
        });

        return roleROS;
    }
}
