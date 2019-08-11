package com.example.boot.springboottemplatedomain.page.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统页面分页RO
 *
 * @author Chang
 * @date 2019/8/11 0:12
 */
@Data
public class PageFindAllRO {

    private Long id;
    private String name;
    private String url;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

    private String pagePermission;

    /**
     * Create PageFindAllRO List. Transfer PO to the RO.
     *
     * @param pages PagePO List
     * @return PageFindAllRO
     */
    public static List<PageFindAllRO> createPageFindAllROS(List<SystemPage> pages) {
        List<PageFindAllRO> pageROS = new ArrayList<>(pages.size());

        pages.forEach(page -> {
            PageFindAllRO pageRO = new PageFindAllRO();
            BeanUtil.copyProperties(page, pageRO);

            String pagePermission = page.getPermissions()
                    .stream().map(SystemPermission::getName)
                    .collect(Collectors.joining(", ", "[", "]"));
            pageRO.setPagePermission(pagePermission);
        });

        return pageROS;
    }
}
