package com.jfshare.mvp.server.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.finagle.thrift.score.ScoreResult;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.dao.JvjindouRuleDao;
import com.jfshare.mvp.server.dao.LevelInfoDao;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample;
import com.jfshare.mvp.server.model.TbLevelInfo;

/**
 * 等级信息
 */
@Service
public class LevelInfoService {
	private static Logger logger = LoggerFactory
			.getLogger(LevelInfoService.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private LevelInfoDao levelInfoDao;
	@Autowired
	private JvjindouRuleDao jvjindouRuleDao;
	@Autowired
	private com.jfshare.mvp.server.finagle.server.ScoreClient scoreClient;

	
	
	// 用户每日查询通过赠送规则赠送积分
	@Transactional
	public void presentJvjindouByuserId(Integer userId) {
		logger.info(String.format("积分同步赠送聚金豆:userId{}", userId));
		TbLevelInfo levlInfo = levelInfoDao.selectLevelInfoByUserId(userId);
		//List<TbLevelInfo> levelInfos = levelInfoDao.selectJvjindouRuleByUserId(userId);
		// 判断当前用户是否有聚金豆， 没有则进行首次添加,有则进行判断今天是否查询过 查询过直接return 没有则按照定义赠送的规则添加聚金豆
		// 查询聚金豆赠送规则
		TbJvjindouRuleExample example = new TbJvjindouRuleExample();
		List<TbJvjindouRule> jvjindouRules = jvjindouRuleDao.selectByExample(example);
		TbJvjindouRule jvjindouRule = null;
		if (jvjindouRules.size() > 0) {
			jvjindouRule = jvjindouRules.get(0);
		} else {
			logger.info(String.format("赠送规则不存在:jvjindouRules{}", jvjindouRules));
			return;
		}
		int num =0;
		String givingRule = jvjindouRule.getGivingRule();
		if (givingRule.equals(Constant.FIXED_PATTERN)) {
			num=jvjindouRule.getFixedGiving();
		} else if (givingRule.equals(Constant.RANDOUM_PATTERN)) {
			int randomGivingMin = jvjindouRule.getRandomGivingMin();
			int randomGivingMax = jvjindouRule.getRandomGivingMax();
			num = getRandomNumInTwoIntNum(randomGivingMin,randomGivingMax);	
		}
		if (levlInfo != null) {
			Date date = new Date();
			//String updateTime = sdf.format(levlInfo.getUpdateTime());
			String queryTime = sdf.format(levlInfo.getQueryTime());
			String curentTime = sdf.format(date);
			if (queryTime.equals(curentTime)) {
				logger.info(String.format("当日已经赠送不能重复赠送:givingRule{}", givingRule));
				return;
			} else {
				levlInfo.setQueryTime(new Date());
				levelInfoDao.updateLevelInfo(levlInfo);
			}
		} else {
			TbLevelInfo info = new TbLevelInfo();
			info.setUserid(userId);
			info.setGrowthPoint(0);
			info.setGrade(Constant.GOLD);
			levelInfoDao.insertSelective(info);
		}
		StringResult results=scoreClient.incomeScore(userId,num, 1, "");
		logger.info(String.format("每日积分同步:results{}", results));
	}


	//更具用户id查询个人中心信息
	public TbLevelInfo queryTbLevelInfo(int userId) {
		logger.info(String.format("查询个人中心信息:userId{}", userId));
		TbLevelInfo levelInfo =levelInfoDao.selectLevelInfoByUserId(userId);
		if(levelInfo!=null) {
			ScoreResult result =scoreClient.getScore(userId);
			logger.info(String.format("积分查询:result{}", result));
			levelInfo.setRealJvjindou(result.getSroce().getAmount());
		}
		return levelInfo;
	}
	
	//聚分享平台同步聚金豆(增加)
	public StringResult addlevelInfo(int userid,int integral,String orderId,int amont) {
		TbLevelInfo levelInfo =levelInfoDao.selectLevelInfoByUserId(userid);
		String  levle=levelInfo.getGrade();
		Double b;
		if(Constant.PLATIMUM.equals(levle)) {
			 b=integral*0.05;
			integral+=Integer.parseInt(b.toString());
		}else if(Constant.BLACK.equals(levle)) {
			 b=integral*0.1;
			integral+=Integer.parseInt(b.toString());
		}else if(Constant.DIAMOND.equals(levle)) {
			 b=integral*0.15;
			integral+=Integer.parseInt(b.toString());
		}
		TbLevelInfo info = levelInfoDao.selectLevelInfoByUserId(userid);
		if(info!=null) {
			info.setRealJvjindou(info.getGrowthPoint()+amont);
			levelInfoDao.updateLevelInfo(info);
		}
		
		StringResult results=scoreClient.incomeScore(userid,integral, 1, orderId);
		logger.info(String.format("积分增加:results{}", results));
		return results;
	}
	//聚分享平台同步聚金豆(减少)
	public StringResult lesslevelInfo(int userid,int integral,String orderId,int amont) {
		TbLevelInfo info = levelInfoDao.selectLevelInfoByUserId(userid);
		if(info!=null) {
			info.setRealJvjindou(info.getGrowthPoint()-amont);
		}
		levelInfoDao.updateLevelInfo(info);
		StringResult results = scoreClient.reduceScore(userid, integral, 1, orderId);
		return results;
	}
	
	public static int getRandomNumInTwoIntNum(int x, int y) {
		int num = 0;
		Random random = new Random();
		num = (random.nextInt(y+1-x)+x);
		return num;
	}
	
}
