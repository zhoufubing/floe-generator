package com.floe.maker.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.floe.maker.generator.JarGenerator;
import com.floe.maker.generator.ScriptGenerator;
import com.floe.maker.generator.file.DynamicFileGenerator;
import com.floe.maker.meta.Meta;
import com.floe.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class MainGenerator extends GenerateTemplate {

    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不要生成精简版程序包啦！");
    }
}