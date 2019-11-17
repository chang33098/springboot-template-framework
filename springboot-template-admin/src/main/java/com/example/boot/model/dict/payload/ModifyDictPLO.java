package com.example.boot.model.dict.payload;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:24
 */
@Data
public class ModifyDictPLO {

    @NotNull
    private Long dictId;
    @NotBlank
    private String name;
    @Length(max = 255)
    private String description;
    @Size(min = 1, max = 50)
    private List<DictOption> options = new ArrayList<>();

    @Data
    @Accessors(chain = true)
    public static class DictOption {
        @NotNull
        @Range(min = 0, max = 127)
        private Integer code;
        @NotBlank
        @Length(max = 255)
        private String value;
    }
}
