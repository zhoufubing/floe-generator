package com.floe.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.floe.maker.meta.enums.FileGenerateTypeEnum;
import com.floe.maker.meta.enums.FileTypeEnum;
import com.floe.maker.meta.enums.ModelTypeEnum;
import lombok.Data;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MetaValidator {
    public static void doValidAndFill(Meta meta){
        validAndFillMetaRoot(meta);
        validAndFillConfig(meta);
        validAndFillmodelConfig(meta);


    }

    private static void validAndFillmodelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        //modelConfig默认值
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if(CollectionUtil.isNotEmpty(modelInfoList)){
            for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList){
                //输出路径默认值
                String fieldName = modelInfo.getFieldName();
                if(StrUtil.isBlank(fieldName)){
                    throw new MetaException("未填写 fieldName");

                }
                String modelInfoType = modelInfo.getType();
                if(StrUtil.isEmpty(modelInfoType)){
                    modelInfo.setType(ModelTypeEnum.STRING.getValue());

                }
            }
        }
    }

    private static void validAndFillConfig(Meta meta) {
        //fileConfig效验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        //sourceRootPath  必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
            //inputRootPath: source+sourceRootPath的最后一个层级的路径

        }
        String inputRootPath = fileConfig.getOutputRootPath();
        String defaultInputRootPath = ".source" + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if(StrUtil.isEmpty(inputRootPath)){
            fileConfig.setInputRootPath(defaultInputRootPath);
        }
            //outputRootPath:默认为当前路径下的generated
            String outputRootPath = fileConfig.getOutputRootPath();
            String defaultOutputRootPath = "generated";
            if (StrUtil.isEmpty(outputRootPath)) {
                    fileConfig.setOutputRootPath(defaultOutputRootPath);
            }
            String fileConfigType = fileConfig.getType();
            String defaultType = FileTypeEnum.DIR.getValue();
            if (StrUtil.isEmpty(fileConfigType)) {
                fileConfig.setType(defaultType);
            }
            //fileInfo默认值
            List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
            if (!CollectionUtil.isNotEmpty(fileInfoList)) {
                return;
            }
            for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
                //inputPath:必填
                String inputPath = fileInfo.getInputPath();
                if (StrUtil.isBlank(inputPath)) {//outputPath ：默认等于inputPath
                    throw new MetaException("未填写 inputPath");
                }
                //outputPath == inputPath
                String outputPath = fileInfo.getOutputPath();
                    if (StrUtil.isEmpty(outputPath)) {
                        fileInfo.setOutputPath(inputPath);
                    }
                    //type:默认inputPath 有文件后缀（如。java）为file,否则为dir
                    String type = fileInfo.getType();
                    if (StrUtil.isBlank(type)) {
                        //无文件后缀
                        if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                            fileInfo.setType(FileTypeEnum.DIR.getValue());
                        } else {
                            fileInfo.setType(FileTypeEnum.FIlE.getValue());
                        }
                    }
                    //generateType ：如果文件结尾不为Ftl,generateType默认为static,否则为dynamic
                    String generateType = fileInfo.getGenerateType();
                    if (StrUtil.isBlank(generateType)) {
                        //为动态模板
                        if (inputPath.endsWith(".ftl")) {
                            fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                    } else {
                        fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                    }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        //基础信息效验和默认值
        String name = StrUtil.blankToDefault(meta.getName(),"my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(),"我的模板代码生成器");
        String author = StrUtil.emptyToDefault(meta.getAuthor(),"floe");
        String basePackage =StrUtil.emptyToDefault(meta.getBasePackage(),"com.floe");
        String version = StrUtil.emptyToDefault(meta.getVersion(),"1.0");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(),DateUtil.now());
        meta.setName(name);
        meta.setDescription(description);
        meta.setAuthor(author);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setCreateTime(createTime);
    }
}
