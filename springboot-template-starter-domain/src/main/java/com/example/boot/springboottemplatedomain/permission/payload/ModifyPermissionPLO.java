package com.example.boot.springboottemplatedomain.permission.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改系统权限PLO
 *
 * @author Chang
 * @date 2019/8/11 18:53
 */
@Data
public class ModifyPermissionPLO {

    @NotNull
    private String name;
    @NotNull
    private String code;
    private String description;

    @NotEmpty
    List<String> matchUrls = new ArrayList<>();
}
