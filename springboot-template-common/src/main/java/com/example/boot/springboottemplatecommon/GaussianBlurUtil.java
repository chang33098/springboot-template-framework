package com.example.boot.springboottemplatecommon;

import com.jhlabs.image.GaussianFilter;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 对图片高斯模糊处理的工具类
 * dependency by: https://mvnrepository.com/artifact/com.jhlabs/filters
 * <p>
 * Created by EDZ on 2019/10/14.
 */
public class GaussianBlurUtil {

    private GaussianBlurUtil() {}

    private static final List<String> ALLOW_PREFIX = Stream.of(".jpg", ".jpeg", ".png").collect(Collectors.toList()); //允许上传的格式

    /**
     * 将原图转出加入高斯模糊的图片并输出到指定路径
     *
     * @param filePath   原文件路径
     * @param outputPath 文件的输出路径
     * @param outputName 文件的输出名称
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void transformAndSave(String filePath, String outputPath, String outputName) throws IllegalArgumentException, IOException {
        Assert.hasLength(filePath, "文件路径不能为空");
        Assert.hasLength("outputName", "输出的文件名不能为空");

        File originalFile = new File(filePath);
        Assert.isTrue(originalFile.exists(), "文件不存在");
        File outputFile = new File(outputPath);
        Assert.isTrue(outputFile.exists(), "输出路径不存在");

        String format = originalFile.getName().substring(originalFile.getName().lastIndexOf(".")); //获取文件后缀
        Assert.isTrue(ALLOW_PREFIX.contains(format.toLowerCase()), "原文件的格式必须为：" + ALLOW_PREFIX.stream().collect(Collectors.joining("|", "[", "]")));
        String outputFormat = outputName.substring(outputName.lastIndexOf(".")); //获取输出文件的后缀
        Assert.isTrue(ALLOW_PREFIX.contains(outputFormat.toLowerCase()), "输出文件的格式必须为：" + ALLOW_PREFIX.stream().collect(Collectors.joining("|", "[", "]")));

        BufferedImage originalImage = ImageIO.read(originalFile); //读取文件
        BufferedImage outputImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB); //转换后的图片

        GaussianFilter gaussianFilter = new GaussianFilter();
        gaussianFilter.setRadius(80); //设置模糊的比率(0~100), 数字越大越模糊
        gaussianFilter.filter(originalImage, outputImage);

        ImageIO.write(outputImage, "jpeg", new File(outputFile, outputName)); //输出文件
    }

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\EDZ\\Desktop\\subzero_desktop.jpg";
        String outputPath = "C:\\Users\\EDZ\\Desktop";
        String outputName = System.currentTimeMillis() + ".jpg";
        transformAndSave(filePath, outputPath, outputName);
    }
}
