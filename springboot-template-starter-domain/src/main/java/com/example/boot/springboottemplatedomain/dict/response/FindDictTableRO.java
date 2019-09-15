package com.example.boot.springboottemplatedomain.dict.response;

import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:23
 */
@Data
public class FindDictTableRO {

    private Long id;
    private String type;
    private String name;
    private String description;
}
