package com.example.boot.springboottemplatedomain.page.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotBlank
    private String url;
    @NotBlank
    @Length(max = 500)
    private String description;
    @Size(min = 1)
    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    public static class PagePermission {
        @NotNull
        private Long permissionId;
        @NotBlank
        private String interceptUrls;
    }
}
