package com.example.boot.model.role.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by EDZ on 2019/8/22.
 */
@Data
public class ModifyRoleRootMenuPLO {

    @Length(max = 50)
    private String icon;
    @NotBlank
    @Length(max = 50)
    private String menuName;
    @NotNull
    private Boolean opened = false;
    @Range(min = 0, max = 127)
    private Integer sortNo = 0;
}
