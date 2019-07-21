package springboottemplatedomain.permission;

import lombok.Data;

import javax.persistence.*;

/**
 * description write this class description...
 *
 * @author ANdady
 * @date 2019/7/21 20:32
 */
@Data
@Entity
@Table(name = "system_permission")
public class SystemPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;


}
