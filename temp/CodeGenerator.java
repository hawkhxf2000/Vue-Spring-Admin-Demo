package com.example.springdemo.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * mp代码生成器
 * by 黄晓峰
 *
 * @since 2022-01-26
 */
public class CodeGenerator {

//    public static void main(String[] args) {
//        generate();
//    }

    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://192.168.2.110:3306/springdemo", "root", "Shawn_801116")
                .globalConfig(builder -> {
                    builder.author("黄晓峰") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\hawkh\\Documents\\GitHub\\springdemo\\src\\main\\java\\com\\example\\springdemo\\codegenerator"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.springdemo") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\hawkh\\Documents\\GitHub\\springdemo\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
//                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("user_demo") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_") // 设置过滤表前缀
                            .addTableSuffix("_demo");  //设置过滤表后缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
