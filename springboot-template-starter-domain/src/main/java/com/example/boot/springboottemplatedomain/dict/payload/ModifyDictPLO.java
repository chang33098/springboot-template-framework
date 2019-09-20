package com.example.boot.springboottemplatedomain.dict.payload;

import lombok.Data;
import javax.validation.constraints.*;
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

    @NotBlank
    private String name;
    @Size(max = 255)
    private String description;
    @Size(min = 1, max = 50)
    private List<DictOption> options = new ArrayList<>();

    @Data
    public static class DictOption {
        @NotNull
        private Integer code;
        @NotBlank
        private String value;
    }
}
