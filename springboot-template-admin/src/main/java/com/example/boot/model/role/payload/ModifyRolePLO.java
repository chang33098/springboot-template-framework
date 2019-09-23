package com.example.boot.model.role.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/17 17:48
 */
@Data
public class ModifyRolePLO {

    @NotBlank
    @Length(max = 50)
    private String name;
    @NotBlank
    @Length(max = 500)
    private String description;
}
