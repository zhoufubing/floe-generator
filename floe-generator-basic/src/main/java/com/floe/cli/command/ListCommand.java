package com.floe.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

@Command(name = "list",description = "View file list",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    @Override
    public void run() {

        String projectPath = System.getProperty("user.dir");
//        System.out.println("---------------");
//        System.out.println(projectPath);
//        System.out.println("---------------");
        File parentFile = new File(projectPath).getParentFile();
//        System.out.println("---------------");
//        System.out.println(parentFile);
//        System.out.println("---------------");
        String inputPath= new File(parentFile, "floe-generator-demo-projects/acm-template").getAbsolutePath();

        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file:
             files) {
           System.out.println(file);
        }
    }
}
