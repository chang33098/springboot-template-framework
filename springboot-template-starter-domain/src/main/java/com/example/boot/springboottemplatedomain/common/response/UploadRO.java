package com.example.boot.springboottemplatedomain.common.response;

import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/31 16:38
 */
@Data
public class UploadRO  {

    private String originalName; //原文件名
    private String fileName; //服务器文件名
    private String resourceLink; //资源链接
}
