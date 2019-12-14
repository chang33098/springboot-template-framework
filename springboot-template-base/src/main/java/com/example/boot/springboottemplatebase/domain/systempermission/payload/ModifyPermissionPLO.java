package com.example.boot.springboottemplatebase.domain.systempermission.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 修改系统权限PLO
 *
 * @author Chang
 * @date 2019/8/11 18:53
 */
@Data
public class ModifyPermissionPLO {

    @NotNull
    private Long permissionId;
    @NotBlank
    @Length(min = 1, max = 50)
    @Pattern(regexp = "^([A-Z][A-Z_]+)$")
    private String permissionCode;
    @NotBlank
    @Length(max = 50)
    private String permissionName;
    @Length(max = 255)
    private String description;
}
