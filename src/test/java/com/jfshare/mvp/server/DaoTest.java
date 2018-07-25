package com.jfshare.mvp.server;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.dao.CommonDao;
import com.jfshare.mvp.server.service.SequenceService;

/**
 * @author fengxiang
 * @date 2018-07-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private SequenceService sequenceService;
	@Test
	public void testCommonDao() {
		List<Map<String, Object>> result = commonDao.executeSql("select sequence_no from tb_sequence where sequence_key='itemno'");
		System.out.println(JSON.toJSONString(result));
	}
	
	@Test
	public void testSequenceService() {
		System.out.println(sequenceService.generalItemNo());
	}

}
