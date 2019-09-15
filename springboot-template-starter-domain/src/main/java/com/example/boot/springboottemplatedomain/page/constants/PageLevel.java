package com.example.boot.springboottemplatedomain.page.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/7 0:02
 */
@Getter
@AllArgsConstructor
public enum PageLevel {

    PARENT(1, "父页面"),
    OPTION(2, "子页面");

    private Integer type;
    private String desc;
}
