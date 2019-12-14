package com.example.boot.springboottemplatebase.base.response;

import lombok.Data;

/**
 * <h1>文件上传response data</h1>
 *
 * @author Chang
 * @date 2019/8/31 16:38
 */
@Data
public class UploadRO {

    /**
     * 原文件名称
     */
    private String originalName;
    /**
     * 服务器文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 资源链接
     */
    private String resourceLink;
}
