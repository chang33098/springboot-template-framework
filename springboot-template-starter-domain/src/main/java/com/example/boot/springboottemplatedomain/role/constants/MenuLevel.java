package com.example.boot.springboottemplatedomain.role.constants;

import lombok.Getter;

/**
 * 页面级别枚举类
 *
 * @author Chang
 * @date 2019/7/22 21:59
 */
@Getter
public enum  MenuLevel {

    PARENT_MENU(1, "父页面"),
    CHILD_MENU(2, "子页面");

    private Integer type;
    private String desc;

    public static boolean parentMenu(int type) {
        return PARENT_MENU.type == type;
    }

    MenuLevel(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
