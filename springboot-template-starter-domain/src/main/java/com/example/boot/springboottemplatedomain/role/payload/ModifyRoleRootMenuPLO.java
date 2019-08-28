package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by EDZ on 2019/8/22.
 */
@Data
public class ModifyRoleRootMenuPLO {

    private String icon;
    @NotBlank
    private String menuName;
    private Integer sortNo = 0;
}
