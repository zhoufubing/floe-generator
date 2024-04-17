package com.floe.generator;

//import com.floe.model.MainTemplateConfig;
import com.floe.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {
    /**
    * 生成
    * @param model 数据模型
    * @throws TemplateException
     * @throws  IOException
    * */
    public static void doGenerator(Object model) throws TemplateException, IOException {
        String inputRootPath = "D:\\Work\\ProgramWorkSpace\\java\\floe-generator\\floe-generator-demo-projects\\acm-template-pro";
        String outputRootPath = "D:\\Work\\ProgramWorkSpace\\java\\floe-generator\\acm-template-pro";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "src/com/floe/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/com/floe/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath,outputPath,model);

        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticGenerator.copyFileByHutool(inputPath,outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath,"README.md").getAbsolutePath();
        StaticGenerator.copyFileByHutool(inputPath,outputPath);




    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("floe");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("result : ");
        doGenerator(mainTemplateConfig);
    }
}
