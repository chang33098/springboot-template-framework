package com.example.boot.springboottemplatedomain.dict.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/15 21:54
 */
@Getter
@AllArgsConstructor
public enum DictLevel {

    PARENT(1, "父页面"),
    OPTION(2, "子页面");

    private Integer level;
    private String desc;
}
