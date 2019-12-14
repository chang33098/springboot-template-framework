package com.example.boot.springboottemplatebase.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 页面级别枚举类
 * <p>
 * 1：父页面
 * 2：子页面
 *
 * @author Chang
 * @date 2019/7/22 21:59
 */
@Getter
@AllArgsConstructor
public enum MenuLevel {

    PARENT_MENU("1"), CHILD_MENU("2");

    private String type;
}
