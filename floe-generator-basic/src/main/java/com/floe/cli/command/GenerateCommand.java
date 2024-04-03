package com.floe.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.floe.generator.MainGenerator;
import com.floe.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@ Command (name = "generator",description = "生成代码",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    @Option(names  = {"-l","--loop"},arity = "0..1",description = "是否循环",interactive = true,echo = true)
    private boolean loop;

    @Option(names = {"-a","--author"},arity = "0..1",description ="Author" ,interactive = true,echo = true)
    private String author = "floe";

    @Option(names = {"-o","--outputText"},arity = "0..1",description = "Output Text",interactive = true,echo = true)
    private String outputText = "Sum = ";
    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this,mainTemplateConfig);
        System.out.println("配置信息：" +mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
