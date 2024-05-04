package com.floe.maker.model;

import lombok.Data;
/*
* 动态模板配置
* */

@Data
public class DataModel {
    /**
    *生成循环
     */
    public boolean loop;
    /**
    作者注释
    *
    */
    public String author = "floe";
    /**
     * 输出
    * */
    public String outputText = "sum = ";
}
