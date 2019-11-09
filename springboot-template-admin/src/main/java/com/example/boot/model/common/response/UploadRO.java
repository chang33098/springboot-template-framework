package com.example.boot.model.common.response;

import lombok.Data;

/**
 * <h1>文件上传response data</h1>
 *
 * @author Chang
 * @date 2019/8/31 16:38
 */
@Data
public class UploadRO {

    private String originalName; //原文件名
    private String fileName; //服务器文件名
    private Long fileSize; //文件大小
    private String resourceLink; //资源链接
}
