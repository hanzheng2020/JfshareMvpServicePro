package com.jfshare.mvp.server.config;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
/**
 * @Description ElasticSearch配置
 * chiwenheng
 */
@Configuration
public class ElasticSearchConfig {
    /**
     * 防止netty的bug
     * java.lang.IllegalStateException: availableProcessors is already set to [8], rejecting [8]
     */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}