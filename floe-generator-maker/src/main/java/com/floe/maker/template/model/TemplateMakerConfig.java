package com.floe.maker.template.model;

import com.floe.maker.meta.Meta;
import com.floe.maker.model.TemplateMakerOutputConfig;
import com.floe.maker.template.model.TemplateMakerFileConfig;
import com.floe.maker.template.model.TemplateMakerModelConfig;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 模版制作配置
 */

@Data
public class TemplateMakerConfig {

    private Meta meta = new Meta();

    private long id;

    private String originProjectPath;

    TemplateMakerFileConfig fileConfig = new TemplateMakerFileConfig();

    TemplateMakerModelConfig modelConfig = new TemplateMakerModelConfig();

    TemplateMakerOutputConfig outputConfig = new TemplateMakerOutputConfig();
}