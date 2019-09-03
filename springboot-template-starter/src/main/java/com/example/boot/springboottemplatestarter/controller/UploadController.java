package com.example.boot.springboottemplatestarter.controller;

import com.example.boot.springboottemplatedomain.common.response.UploadRO;
import com.example.boot.springboottemplatestarter.properties.CustomUploadConfiguration;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/31 12:02
 */
@Slf4j
@RestController
@RequestMapping(value = "system/upload")
@EnableConfigurationProperties(CustomUploadConfiguration.class)
public class UploadController {

    private final CustomUploadConfiguration customUploadConfiguration;

    @Autowired
    public UploadController(CustomUploadConfiguration customUploadConfiguration) {
        this.customUploadConfiguration = customUploadConfiguration;
    }

    @PostMapping(value = "image")
    public ResponseBodyBean image(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        final String originalName = file.getOriginalFilename();
        assert originalName != null;
        final String suffix = originalName.substring(originalName.lastIndexOf("."));
        final String fileName = System.currentTimeMillis() + suffix; //自定义文件名

        try {
            File path = new File(customUploadConfiguration.getImage().getPath());
            if (!path.exists())
                path.mkdir();

            File image = new File(path, fileName);
            file.transferTo(image); //文件传输

            UploadRO uploadRO = new UploadRO();
            uploadRO.setFileName(fileName);
            uploadRO.setOriginalName(originalName);
            uploadRO.setResourceLink(customUploadConfiguration.getImage().getDomain() + fileName);

            log.info("图片上传成功 filepath: {}", image.getPath());
            return ResponseBodyBean.ofSuccess(uploadRO, "上传成功");
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return ResponseBodyBean.ofFailure(e.getMessage());
        } catch (IOException | IllegalStateException e) {
            log.error(e.getMessage(), e);
            return ResponseBodyBean.ofFailure("上传图片出错 " + e.getMessage());
        }
    }
}
