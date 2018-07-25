package com.jfshare.mvp.server.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.CommonDao;

/**
 * 获取sequence，用于生成编号
 * @author fengxiang
 * @date 2018-07-25
 */
@Service
public class SequenceService {
	@Autowired
	private CommonDao commonDao;
	
	@Transactional
	private synchronized int getSequenceNo(String key) {
		String querysql = "select sequence_no from tb_sequence where sequence_key='" + key + "'";
		List<Map<String, Object>> resultList = commonDao.executeSql(querysql);
		if (CollectionUtils.isEmpty(resultList)) {
			String insertSql = "insert into tb_sequence(sequence_key, sequence_no) values('"+key+"',0);";
			commonDao.executeSql(insertSql);
			return 0;
		}
		int sequenceNo = (int) resultList.get(0).get("sequence_no") + 1;
		String updateSql = "update tb_sequence set sequence_no=" + sequenceNo + " where sequence_key='" + key + "'";
		commonDao.executeSql(updateSql);
		return sequenceNo;
	}
	
	public String generalItemNo() {
		String sequenceNo = getSequenceNo("itemNo") + "";
		String itemNo = "item" + StringUtils.leftPad(sequenceNo, 6, "0");
		return itemNo;
	}
}
