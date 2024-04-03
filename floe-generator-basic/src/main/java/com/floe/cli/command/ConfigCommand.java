package com.floe.cli.command;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ReflectUtil;
import com.floe.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;
import java.sql.SQLOutput;

@Command(name = "config",description = "View parameter information",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        System.out.println("查看参数信息");

        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        for (Field field:
             fields) {
            System.out.println("字段名称："+ field.getName());
            System.out.println("字段名称："+ field.getType());
            System.out.println("---");
        }
    }

}
