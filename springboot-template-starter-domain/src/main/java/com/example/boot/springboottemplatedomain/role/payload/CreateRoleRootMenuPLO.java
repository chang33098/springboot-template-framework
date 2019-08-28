package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/20 0:06
 */
@Data
public class CreateRoleRootMenuPLO {

    private String icon;
    @NotBlank
    private String menuName;
    private Integer sortNo = 0;
}
