package com.example.boot.springboottemplatedomain.user.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/28 22:27
 */
@Data
public class CreateUserPLO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    @NotBlank
    private String nickname;
    private String avatar;
    private String description;
}
