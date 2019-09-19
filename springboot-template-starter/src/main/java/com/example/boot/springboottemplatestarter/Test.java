package com.example.boot.springboottemplatestarter;

import cn.hutool.core.util.ReUtil;

import java.util.regex.Pattern;

/**
 * Created by EDZ on 2019/9/19.
 */
public class Test {

    public static void main(String[] args) {
        String content = "DAA4";
        String content2 = "FHDU_SD";
        String content3 = "_HUDE_DSHU_";
        String content4 = "SHDU_SDUa";

//        零和非零开头的数字：^(0|[1-9][0-9]*)$
//        英文和数字：^[A-Za-z0-9]+$ 或 ^[A-Za-z0-9]{4,40}$
//        由数字、26个英文字母或者下划线组成的字符串：^\w+$ 或 ^\w{3,20}$

        Pattern pattern = Pattern.compile("^([A-Z][A-Z_]+)$");

        System.out.println(ReUtil.contains(pattern, content));
        System.out.println(ReUtil.contains(pattern, content2));
        System.out.println(ReUtil.contains(pattern, content3));
        System.out.println(ReUtil.contains(pattern, content4));
    }
}
