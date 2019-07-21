package springboottemplatedomain.user.constants;

import lombok.Getter;

/**
 * description write this class description...
 *
 * @author ANdady
 * @date 2019/7/21 19:53
 */
@Getter
public enum  UserStatus {

    ENABLE(1, "启用"),
    DISABLE(2, "禁用");

    private Integer status;
    private String desc;

    UserStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
