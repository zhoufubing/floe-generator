package com.floe.model;

import lombok.Data;
/*
* 动态模板配置
* */

@Data
public class MainTemplateConfig {
    /*
    *生成循环
    * */
    private boolean loop;
    /*作者注释
    * */
    private String author = "floe";
    /*输出
    * */
    private String outputText = "sum = ";
}
