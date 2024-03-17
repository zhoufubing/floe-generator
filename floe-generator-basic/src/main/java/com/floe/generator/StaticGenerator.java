package com.floe.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/*
* 静态文件生成
*/
public class StaticGenerator {
    /**
     * 拷贝文件
     * @param inputPath     输入路径
     * @param outputPath    输出路径
     * */
    public static void copyFileByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }
    public static void main(String[] args) {
        //获取根目录
        String projectPath = System.getProperty("user.dir");
         System.out.println(projectPath);
        File parentFile = new File(projectPath).getParentFile();

        //获取模板代码
        String inputPath = new File(parentFile, "floe-generator/floe-generator-demo-projects/acm-template").getAbsolutePath();
        //直接输出
        String outputPath = projectPath;
        copyFileByHutool(inputPath, outputPath);
    }

    public static void copyFilesByRecursive(String inputPath,String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile,outputFile);
        }catch (Exception e){
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }
/**
 * 先创建目录，再去遍历目录下的文件，依次复制
 * @param inputFile 输入
 * @param outputFile 输出
 * @throws IOException 抛出
 */

    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException{
        //判断文件OR目录
        if(inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            //是目录则创建目录
            if(!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            //获取目录下的所有文件和子级
            File[] files = inputFile.listFiles();
            //无文件，return
            if(ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file:
                 files) {
                //递归拷贝下一层文件
                copyFileByRecursive(file,destOutputFile);
            }
        }else {
            //是文件，复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}

