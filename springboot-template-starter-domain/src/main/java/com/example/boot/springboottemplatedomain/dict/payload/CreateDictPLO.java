package com.example.boot.springboottemplatedomain.dict.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:23
 */
@Data
public class CreateDictPLO {

    // TODO: 2019/9/15 添加正则表达式的校验 

    @NotBlank
    @Pattern(regexp = "^([A-Z][A-Z_]+)$")
    private String type;
    @NotBlank
    private String name;
    @Length(max = 255)
    private String description;
    @Size(min = 1)
    private List<DictOption> options = new ArrayList<>();

    @Data
    public static class DictOption {
        @NotNull
        private Integer code;
        @NotBlank
        private String value;
    }
}
