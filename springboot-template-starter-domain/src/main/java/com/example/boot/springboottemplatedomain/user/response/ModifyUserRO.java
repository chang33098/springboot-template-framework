package com.example.boot.springboottemplatedomain.user.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/2 22:50
 */
@Data
public class ModifyUserRO {

    private String username;
    private String phone;
    private String nickname;
    private String avatar;
    private String description;
    private Long roleId;
    private List<UserRole> userRoles = new ArrayList<>();

    @Data
    public static class UserRole {
        private Long roleId;
        private String roleName;
        private Boolean checked = false;
    }
}
