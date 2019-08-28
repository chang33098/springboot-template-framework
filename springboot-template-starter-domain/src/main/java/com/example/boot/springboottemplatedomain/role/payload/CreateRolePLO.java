package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建系统角色PLO
 * Created by EDZ on 2019/8/12.
 */
@Data
public class CreateRolePLO {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
