package com.example.boot.springboottemplatedomain.permission.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 修改系统权限PLO
 *
 * @author Chang
 * @date 2019/8/11 18:53
 */
@Data
public class ModifyPermissionPLO {

    @NotNull
    private String name;
    @NotNull
    private String code;
    private String description;
}
