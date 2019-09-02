package com.example.boot.springboottemplatedomain.user.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/28 22:27
 */
@Data
public class ModifyUserPLO {

    @NotBlank
    private String phone;
    @NotBlank
    private String nickname;
    private String avatar;
    private String description;
    @NotNull
    private Long roleId;
}
