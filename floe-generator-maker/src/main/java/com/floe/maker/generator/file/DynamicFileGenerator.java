package com.floe.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import com.floe.maker.model.DataModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
//import javafx.scene.shape.Path;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicFileGenerator {

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
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("floe");
        dataModel.setLoop(false);
        dataModel.setOutputText("sum  =  ");

        //文件不存在则创建文件和目录
        if(!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }
        //生成
        Writer out= new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(outputPath)), StandardCharsets.UTF_8));
        template.process(model,out);
        out.close();
    }
}
