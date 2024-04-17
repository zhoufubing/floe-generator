package com.floe.generator;

import cn.hutool.core.io.FileUtil;
import com.floe.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
//import javafx.scene.shape.Path;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {

        String projectPath = System.getProperty("user.dir");
        //test
        System.out.println(projectPath);

        String inputPath = projectPath + File.separator+"src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath+File.separator + "MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setOutputText("结果");
        mainTemplateConfig.setAuthor("floe");
        mainTemplateConfig.setLoop(false);
        doGenerate(inputPath,outputPath,mainTemplateConfig);


    }

    /*
    * 生产文件
    * @param inputPath 模板文件输入路径
    * @param outputPath 输出路径
    * @throws IOException
    * @throws TemplateException
     */
    public static void doGenerate(String inputPath,String outputPath,Object model) throws IOException, TemplateException {

        //new 出对象 参数为版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        //指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName,"utf-8");

        //创建模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("floe");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("sum  =  ");


        if(!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }
        //生成
        Writer out= new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(outputPath)), StandardCharsets.UTF_8));
        template.process(model,out);
        out.close();
    }
}
