package com.example.boot.model.role.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/20 0:06
 */
@Data
public class CreateRoleRootMenuPLO {

    @Length(max = 50)
    private String icon;
    @NotBlank
    @Length(max = 50)
    private String menuName;
    @Range(min = 0, max = 127)
    private Integer sortNo = 0;
}