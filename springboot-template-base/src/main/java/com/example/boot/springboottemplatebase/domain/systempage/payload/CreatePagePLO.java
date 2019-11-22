package com.example.boot.springboottemplatebase.domain.systempage.payload;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Accessors(chain = true)
public class CreatePagePLO {

    @NotBlank
    @Length(max = 50)
    private String name;
    @NotBlank
    @Length(min = 1, max = 50)
//    @Pattern(regexp = "^([A-Z][A-Z_]+)$")
    private String code;
    @NotBlank
    @Length(max = 100)
//    @Pattern(regexp = "^[A-Za-z0-9_/-]+$")
    private String url;
    @NotBlank
    @Length(max = 500)
    private String description;
    @Size(min = 1)
    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    @Accessors(chain = true)
    public static class PagePermission {
        @NotNull
        private Long permissionId;
        @NotBlank
        @Length(max = 500)
        private String interceptUrls;
    }
}
