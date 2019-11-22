package com.example.boot.springboottemplatebase.domain.systemrole.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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
    private Long roleId;
    @NotBlank
    @Length(max = 50)
    private String name;
    @NotBlank
    @Length(max = 500)
    private String description;
}
