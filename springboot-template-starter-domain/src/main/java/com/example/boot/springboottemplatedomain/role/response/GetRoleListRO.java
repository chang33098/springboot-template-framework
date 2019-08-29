package com.example.boot.springboottemplatedomain.role.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/29 23:26
 */
@Data
public class GetRoleListRO {

    private Long id;
    private String name;

    public static List<GetRoleListRO> create(List<SystemRole> roles) {
        List<GetRoleListRO> roleROS = new ArrayList<>();
        roles.forEach(role -> {
            GetRoleListRO roleRO = new GetRoleListRO();
            BeanUtil.copyProperties(role, roleRO);
            roleROS.add(roleRO);
        });

        return roleROS;
    }
}
