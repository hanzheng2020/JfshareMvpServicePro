package com.jfshare.mvp.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatPayInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    WeChatPayInterface wxpay;
    
    @Autowired
    AliPayInterface alipay;
    
	@Test
	public void testUtils() {
		/*TbDirectPayProduct bean = new TbDirectPayProduct();
		bean.setId(1);
		bean.setCreatedTime(new Date());
		bean.setProductDesc("test");
		System.out.println(JSON.toJSONString(ConvertBeanToMapUtils.convertBeanToMap(bean)));*/
		
		
//		wxpay.createPrepayId("test", "123", 1, "127.0.0.1");
		alipay.createPaySign();
		
	}
}
