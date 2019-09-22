package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

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
    @Range(min = 0, max = 127)
    private Integer sortNo = 0;
}
