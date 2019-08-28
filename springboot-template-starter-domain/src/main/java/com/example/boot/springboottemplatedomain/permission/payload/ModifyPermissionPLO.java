package com.example.boot.springboottemplatedomain.permission.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改系统权限PLO
 *
 * @author Chang
 * @date 2019/8/11 18:53
 */
@Data
public class ModifyPermissionPLO {

    @NotBlank
    private String name;
    @NotBlank
    private String code;
    private String description;
}
