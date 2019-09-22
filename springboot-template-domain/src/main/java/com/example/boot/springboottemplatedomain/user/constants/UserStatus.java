package com.example.boot.springboottemplatedomain.user.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description write this class description...
 * <p>
 * 1：启用
 * 2：禁用
 *
 * @author Chang
 * @date 2019/7/21 19:53
 */
@Getter
@AllArgsConstructor
public enum UserStatus {

    ENABLED(1), DISABLED(2);

    private Integer status;
}
