package com.floe.generator;

import com.floe.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {
    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        //整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        System.out.println(parentFile);
        //输入路径
        String inputPath = new File(projectPath, "floe-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        System.out.println("3:"+inputPath+"\n"+outputPath);
        StaticGenerator.copyFilesByRecursive(inputPath,outputPath);

        String inputDynamicFilePath = projectPath + File.separator + "floe-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator +"MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);


    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("floe");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("result : ");
        doGenerator(mainTemplateConfig);
    }
}
