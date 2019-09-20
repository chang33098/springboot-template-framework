package com.example.boot.springboottemplatedomain.user.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/28 22:27
 */
@Data
public class ModifyUserPLO {

    @NotBlank
    @Pattern(regexp = "^1([3456789])\\d{9}$")
    private String phone;
    @NotBlank
    @Length(max = 100)
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$")
    private String nickname;
    private String avatar;
    @Length(max = 500)
    private String description;
    @NotNull
    private Long roleId;
}
