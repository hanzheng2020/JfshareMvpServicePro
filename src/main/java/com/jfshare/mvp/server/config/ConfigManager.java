package com.jfshare.mvp.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * 获取ZK或者Apollo中的配置
 * 获取集中配置管理项操作类
 *
 */
@Component
public class ConfigManager {
	

    protected static final Logger logger = LoggerFactory
            .getLogger(ConfigManager.class);
    
    /**
     * 根据appKey, configKey获取配置值
     *
     * @param appKey
     * @param configKey
     * @return
     */
    public String getConfigValue(String appKey, String configKey) {

        return this.getConfigValue(appKey,configKey,"");
    }

    /**
     * 根据appKey, configKey获取配置值, 如果没有, 则返回默认值
     *
     * @param appKey
     * @param configKey
     * @param defValue
     * @return
     */
    public String getConfigValue(String appKey, String configKey, String defValue) {

        Config config = ConfigService.getConfig(appKey);
        String configValue = config.getProperty(configKey, defValue);

        return configValue;
    }
}
