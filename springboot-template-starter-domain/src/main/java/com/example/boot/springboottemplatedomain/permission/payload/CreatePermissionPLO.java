package com.example.boot.springboottemplatedomain.permission.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 创建系统权限
 *
 * @author Chang
 * @date 2019/8/11 18:50
 */
@Data
public class CreatePermissionPLO {

    @NotNull
    private String name;
    @NotNull
//    @Pattern(regexp = "/[^A-Z|\\-|_|]/g", message = "请输入正确的权限代码")
    private String code;
    private String description;
}
