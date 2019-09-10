package com.example.boot.springboottemplatedomain.dict.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank
    private String type;
    @NotBlank
    private String name;
    @Max(255)
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
