package com.jfshare.mvp.server.finagle.server;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.finagle.thrift.order.OrderDetailResult;
import com.jfshare.finagle.thrift.order.OrderServ;
import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.mvp.server.config.ConfigManager;
import com.twitter.util.Await;

/**
 * @author fengxiang
 * @date 2018-08-17
 */
@Service
public class OrderClient {
    private Logger logger = LoggerFactory.getLogger(OrderClient.class);
    private OrderServ.ServiceIface service = null;
    @Autowired
    private ConfigManager configManager;

    @PostConstruct
    public void init() {
        // 获取zk地址
        String zkPath = configManager.getConfigValue("zk-boot", "zk_addr");
        logger.info("zk info "+zkPath);
        service = Utils4Brain.createThriftClient(zkPath, Constants4Brain.Jfshare_orderServ,
                OrderServ.ServiceIface.class);
    }

    //查询订单信息
    public OrderDetailResult queryOrderDetail(String userId, String orderId) {
        try {
        	OrderDetailResult result = Await.result(service.queryOrderDetail(1, Integer.valueOf(userId), orderId));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用service.getScore失败！", e);
        }
        return null;
    }
}
