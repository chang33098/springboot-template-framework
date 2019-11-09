package com.example.boot;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * MyBatis代码生成器
 *
 * @author Chang
 * @date 2019/11/9 21:38
 */
public class MyBatisAutoGenerator {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/springboot_template_framework?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String user = "root"; //数据库账号
    private static final String password = "root"; //数据库密码
    private static final String[] tableName = {"mybatis_test1"};
    private static final String packageName = "com.example.boot";
    private static final String author = "chang_";
    private static final String outputPath = "G:\\WORKSPACE\\AutoGeneratorOutPut";

    private static final String entitySuperPackageName = "com.example.boot.springboottemplatedomain";
    private static final String[] entityColumns =
            {
                    "id",
                    "createBy",
                    "createTime",
                    "updateBy",
                    "updateTime",
                    "deleted"
            }; //定义通过字段

    public static void main(String[] args) {
        //数据源的配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL).setUrl(url);
        dataSourceConfig.setUsername(user);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setDriverName(driverName);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setSuperEntityClass(entitySuperPackageName);
        strategyConfig.setSuperEntityColumns(entityColumns);
        strategyConfig.setSuperEntityClass("com.baomidou.ant.common.BaseEntity"); //设置父类
        strategyConfig.setCapitalMode(true); //是否大写命名
        strategyConfig.setEntityLombokModel(true); //是否为lombok模型(默认为false)
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); //下划线的命名
        strategyConfig.setLogicDeleteFieldName("deleted"); //设置逻辑删除的字段名称
        strategyConfig.setInclude(tableName);//修改替换成你需要的表名，多个表名传数组
        strategyConfig.setEntityTableFieldAnnotationEnable(true); //生成字段注解
        strategyConfig.setSuperControllerClass("com.baomidou.ant.common.BaseController"); //设置父类controller

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false);
        globalConfig.setEnableCache(false); //是否开启二级缓存
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setAuthor(author);
        globalConfig.setOutputDir(outputPath);
        globalConfig.setFileOverride(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setServiceName("I%sService");//user -> IUserService, 设置成true: user -> IUserService

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);

        //代码生成器配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.setPackageInfo(new PackageConfig().setParent(packageName).setController("controller").setEntity("entity")).execute();
    }
}
