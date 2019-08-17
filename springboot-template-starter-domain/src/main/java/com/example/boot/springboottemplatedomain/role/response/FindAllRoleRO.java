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
public class FindAllRoleRO {

    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private Timestamp updateTime;

    public static List<FindAllRoleRO> createFindAllRoleROS(List<SystemRole> roles) {
        List<FindAllRoleRO> roleROS = new ArrayList<>();

        roles.forEach(role -> {
            FindAllRoleRO roleRO = new FindAllRoleRO();
            BeanUtil.copyProperties(role, roleRO);

            roleROS.add(roleRO);
        });

        return roleROS;
    }
}
