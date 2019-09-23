package com.example.boot.model.dict.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/18 23:24
 */
@Data
public class ModifyDictRO {

    private Long id;
    private String name;
    private String type;
    private String description;
    private List<DictOption> options = new ArrayList<>();

    @Data
    public static class DictOption {
        private Integer code;
        private String value;
    }
}
