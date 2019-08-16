package com.example.boot.springboottemplatedomain.page.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统页面分页RO
 *
 * @author Chang
 * @date 2019/8/11 0:12
 */
@Data
public class FindAllPageRO {

    private Long id;
    private String code;
    private String name;
    private String url;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

    /**
     * Create FindAllPageRO List. Transfer PO to the RO.
     *
     * @param pages PagePO List
     * @return FindAllPageRO
     */
    public static List<FindAllPageRO> createFindAllPageROS(List<SystemPage> pages) {
        List<FindAllPageRO> pageROS = new ArrayList<>(pages.size());

        pages.forEach(page -> {
            FindAllPageRO pageRO = new FindAllPageRO();
            BeanUtil.copyProperties(page, pageRO);
            pageROS.add(pageRO);
        });

        return pageROS;
    }
}
