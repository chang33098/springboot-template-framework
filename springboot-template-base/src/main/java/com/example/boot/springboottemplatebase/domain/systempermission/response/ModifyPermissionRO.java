package com.example.boot.springboottemplatebase.domain.systempermission.response;

import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/12 0:43
 */
@Data
public class ModifyPermissionRO {

    private Long id;
    private String name;
    private String code;
    private String description;
}
