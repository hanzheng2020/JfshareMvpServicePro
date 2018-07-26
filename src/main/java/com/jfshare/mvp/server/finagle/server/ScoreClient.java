package com.jfshare.mvp.server.finagle.server;


import com.jfshare.finagle.thrift.score.ScoreResult;
import com.jfshare.finagle.thrift.score.ScoreServ;
import com.jfshare.mvp.server.config.ConfigManager;
import com.twitter.util.Await;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *  chiwenheng
 *  finagle RPC
 */
@Service
public class ScoreClient {
    private Logger logger = LoggerFactory.getLogger(ScoreClient.class);
    private ScoreServ.ServiceIface service = null;
    @Autowired
    private ConfigManager configManager;

    @PostConstruct
    public void init() {
        // 获取zk地址
        String zkPath = configManager.getConfigValue("zk-boot", "zk_addr");
        logger.info("zk info "+zkPath);
        service = Utils4Brain.createThriftClient(zkPath, Constants4Brain.Jfshare_ScoreServ,
                ScoreServ.ServiceIface.class);
    }


    public ScoreResult getScore(int userId) {
        try {
            ScoreResult result = Await.result(service.getScore(userId));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用service.getScore失败！", e);
        }
        return null;
    }
}
