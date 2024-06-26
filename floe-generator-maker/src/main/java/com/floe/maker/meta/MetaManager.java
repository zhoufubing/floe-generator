package com.floe.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {
    private static volatile Meta meta;
    private MetaManager(){
        //私有，防止实例化
    };
    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if(meta == null){
                    meta = initMeta();
                }
            }
        }
        return meta;
    }
    private static Meta initMeta(){
//        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        String metaJson = ResourceUtil.readUtf8Str("springboot-init-meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        MetaValidator.doValidAndFill(newMeta);
        return newMeta;
    }
}
