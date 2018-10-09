package com.jfshare.mvp.server.finagle.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.jfshare.finagle.thrift.stock.BatchQueryParam;
import com.jfshare.finagle.thrift.stock.BatchStockResult;
import com.jfshare.finagle.thrift.stock.StockServ;
import com.jfshare.mvp.server.config.ConfigManager;
import com.twitter.util.Await;

public class StockClient {
	private StockServ.ServiceIface service = null;
	@Autowired
	private ConfigManager configManager;
	
	@PostConstruct
	public void init() {
		// 获取zk地址
		String zkPath = configManager.getConfigValue("zk-boot", "zk_addr");
		service = Utils4Brain.createThriftClient(zkPath, Constants4Brain.Jfshare_stockServ,
				StockServ.ServiceIface.class);
	}
	
	public BatchStockResult batchQueryStock(String productId) throws Exception{
		BatchQueryParam param = new BatchQueryParam();
		param.setQueryType("tatal");
		List<String> list = new ArrayList<String>();
		list.add(productId);
		BatchStockResult result = Await.result(service.batchQueryStock(param));
		return result;
	}
}
