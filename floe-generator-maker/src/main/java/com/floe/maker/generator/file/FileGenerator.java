package com.floe.maker.generator.file;

import com.floe.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class FileGenerator {
    /**
    * 生成
    * @param model 数据模型
    * @throws TemplateException
     * @throws  IOException
    * */
    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");

        //整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();

        //输入路径
        String inputPath = new File(parentFile, "floe-generator-demo-projects/acm-template").getAbsolutePath();


        String outputPath = projectPath;
//        静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath,outputPath);
//动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator +"acm-template/src/com/floe/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);


    }

    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("floe");
        dataModel.setLoop(false);
        dataModel.setOutputText("result : ");
        doGenerator(dataModel);
    }
}
