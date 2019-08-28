package com.example.boot.springboottemplatedomain.user.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description write this class description...
 *
 * @author ANdady
 * @date 2019/7/21 19:53
 */
@Getter
@AllArgsConstructor
public enum UserStatus {

    ENABLED(1, "启用"),
    DISABLED(2, "禁用");

    private Integer status;
    private String desc;
}
