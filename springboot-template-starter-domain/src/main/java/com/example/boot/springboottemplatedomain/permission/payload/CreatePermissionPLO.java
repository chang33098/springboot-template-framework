package com.example.boot.springboottemplatedomain.permission.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建系统权限
 *
 * @author Chang
 * @date 2019/8/11 18:50
 */
@Data
public class CreatePermissionPLO {

    @NotBlank
    private String name;
    @NotBlank
    private String code;
    private String description;
}
