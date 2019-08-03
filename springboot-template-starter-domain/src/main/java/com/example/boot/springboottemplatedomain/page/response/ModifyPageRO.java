package com.example.boot.springboottemplatedomain.page.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 15:22
 */
@Data
public class ModifyPageRO {

    private String name;
    private String url;
    private String description;

    public static ModifyPageRO transferPageRO(SystemPage page) {
        ModifyPageRO pageRO = new ModifyPageRO();
        BeanUtil.copyProperties(page, pageRO);

        return pageRO;
    }
}
