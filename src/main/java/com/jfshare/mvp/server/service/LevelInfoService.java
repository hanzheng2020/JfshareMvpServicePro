package com.jfshare.mvp.server.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.JvjindouRuleDao;
import com.jfshare.mvp.server.dao.LevelInfoDao;
import com.jfshare.mvp.server.mapper.TbLevelInfo;

/**
 * 等级信息
 */
@Service
public class LevelInfoService {
	@Autowired
	private LevelInfoDao levelInfoDao;
	//消费聚金豆
	@Transactional
	public int openOrdisableJvjindou(int userId, int jvjindou){
		List<TbLevelInfo> levelInfos = levelInfoDao.selectJvjindouRuleByUserId(userId);
		for (TbLevelInfo tbLevelInfo : levelInfos) {
			tbLevelInfo.setRealJvjindou(tbLevelInfo.getRealJvjindou()-jvjindou);
			tbLevelInfo.setUpdateTime(new Date());
			tbLevelInfo.setRemark("消费聚金豆"+jvjindou);
			levelInfoDao.updateLevelInfoById(tbLevelInfo);
		}
		return 0;
	}
	//查询聚金豆
	public TbLevelInfo selectByuserid(int userid) {
		levelInfoDao.selectLevelInfoById(userid);
		return new TbLevelInfo();
	}

}
