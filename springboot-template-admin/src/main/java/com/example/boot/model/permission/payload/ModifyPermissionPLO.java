package com.example.boot.model.permission.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 修改系统权限PLO
 *
 * @author Chang
 * @date 2019/8/11 18:53
 */
@Data
public class ModifyPermissionPLO {

    @NotBlank
    @Length(max = 50)
    private String name;
    @NotBlank
    @Length(min = 1, max = 50)
    @Pattern(regexp = "^([A-Z][A-Z_]+)$")
    private String code;
    @Length(max = 255)
    private String description;
}