package com.example.boot.springboottemplatedomain.page.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/1 0:04
 */
@Data
public class CreatePagePLO {

    // TODO: 2019/8/13 添加[code]的正则校验
    // TODO: 2019/8/13 添加[url]的正则校验

    @NotNull
    private String name;
    @NotNull
    private String code;
    @NotNull
    private String url;
    @NotNull
    @Length(max = 500)
    private String description;
    @NotEmpty
    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    public static class PagePermission {
        @NotNull
        private Long permissionId;
        @NotNull
        private String interceptUrls;
    }
}
