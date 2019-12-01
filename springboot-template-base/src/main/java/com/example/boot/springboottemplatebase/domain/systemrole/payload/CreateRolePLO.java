package com.example.boot.springboottemplatebase.domain.systemrole.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 创建系统角色PLO
 * Created by EDZ on 2019/8/12.
 */
@Data
public class CreateRolePLO {

    @NotBlank
    @Length(max = 50)
    private String roleName;
    @NotBlank
    @Length(max = 500)
    private String description;
}