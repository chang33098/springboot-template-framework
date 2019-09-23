package com.example.boot.model.dict.payload;

import com.example.boot.model.common.payload.AbstractPaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:10
 */
@Data
public class FindDictTablePLO extends AbstractPaginationPLO {

    private String type;
    private String name;
}
