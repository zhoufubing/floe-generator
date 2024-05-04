package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;


/*
* 静态文件生成
*/
public class StaticGenerator {
    /**
    * 拷贝文件
    * @param inputPath     输入路径
    * @param outputPath    输出路径
     */
public static void copyFilesByHuTool(String inputPath, String outputPath){
    FileUtil.copy(inputPath,outputPath,false);
    }
}

