package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/17 17:48
 */
@Data
public class ModifyRolePLO {

    @NotNull
    private String name;
    @NotNull
    private String description;
}
