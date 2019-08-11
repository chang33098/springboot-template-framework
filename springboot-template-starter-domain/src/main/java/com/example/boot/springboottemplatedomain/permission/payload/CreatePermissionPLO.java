package com.example.boot.springboottemplatedomain.permission.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    private String code;
    private String description;
    @NotEmpty
    private List<PermissionUrl> matchUrls = new ArrayList<>();

    @Data
    public static class PermissionUrl {
        private String matchUrl;
        private Integer sortNo;
    }
}
