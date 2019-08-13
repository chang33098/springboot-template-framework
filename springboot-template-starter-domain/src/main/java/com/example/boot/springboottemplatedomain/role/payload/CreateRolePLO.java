package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 创建系统角色PLO
 * Created by EDZ on 2019/8/12.
 */
@Data
public class CreateRolePLO {

    @NotNull
    private String name;
    @NotNull
    private String description;

    public static class Permission {

    }
}
