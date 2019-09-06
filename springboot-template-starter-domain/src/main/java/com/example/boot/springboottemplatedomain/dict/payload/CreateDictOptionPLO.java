package com.example.boot.springboottemplatedomain.dict.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:26
 */
@Data
public class CreateDictOptionPLO {

    @NotNull
    private Long parentId;
    @Size(min = 1)
    private List<DictOption> dictOptions = new ArrayList<>();

    @Data
    public static class DictOption {
        private Integer code;
        private String value;
    }
}
