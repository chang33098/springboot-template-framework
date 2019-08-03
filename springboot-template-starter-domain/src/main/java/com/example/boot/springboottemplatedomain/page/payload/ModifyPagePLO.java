package com.example.boot.springboottemplatedomain.page.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 14:46
 */
@Data
public class ModifyPagePLO {

    @NotNull
    private String name;
    @NotNull
    private String url;
    @NotNull
    @Length(max = 500)
    private String description;
}
