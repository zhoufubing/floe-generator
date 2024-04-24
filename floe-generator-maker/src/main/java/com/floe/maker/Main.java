package com.floe.maker;


import com.floe.maker.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {

//        args =new String[]{"generator", "-l", "-a", "-o"};
//        args =new String[]{"config"};
//        args =new String[]{"list"};
//        CommandExecutor commandExecutor = new CommandExecutor();
//        commandExecutor.doExecute(args);
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
    }
