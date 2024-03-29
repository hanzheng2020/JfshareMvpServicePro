package com.jfshare.mvp.server.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.finagle.thrift.result.Result;
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

	private  int type=16;//同步赠送积分类型（积分查询赠送）
	
	
	// 用户每日查询通过赠送规则赠送积分
	@Transactional
	public Map<String,Object> presentJvjindouByuserId(Integer userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", false);//是否是当日第一次赠送  false:否，true：是
		map.put("amount", 0);//当次赠送的聚金豆数量
		
		logger.info("积分同步赠送聚金豆:userId{}", userId);
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
			return map;
		}
		int num =0;
		int status=1;
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
			String queryTime = null;
			if(levlInfo.getQueryTime()!=null) {
				queryTime = sdf.format(levlInfo.getQueryTime());
			}

			String curentTime = sdf.format(date);
			if (curentTime.equals(queryTime)) {
				logger.info("当日已经赠送不能重复赠送:givingRule{}", givingRule);
				return map;
			} else {
				long nuber = new Date().getTime();
				String tradeid= nuber+"-"+userId;
				logger.info("每日积分同步:tradeid:"+tradeid);
				StringResult results=scoreClient.incomeScore(userId,num,type, tradeid);
				logger.info("每日积分同步:results{}", results);
				status=results.getResult().code;
				if(0==results.getResult().code) {
					levlInfo.setQueryTime(new Date());
					levlInfo.setRealJvjindou(num);
					levelInfoDao.updateLevelInfo(levlInfo);
				}

			}
		} else {
			StringResult results=scoreClient.incomeScore(userId,num, type, "0");
			logger.info("每日积分同步:results{}", results);
			status=results.getResult().code;
			if(0==results.getResult().code) {
				TbLevelInfo info = new TbLevelInfo();
				info.setUserid(userId);
				info.setGrowthPoint(0);
				info.setGrade(Constant.GOLD);
				info.setRealJvjindou(num);
				info.setQueryTime(new Date());
				levelInfoDao.insertSelective(info);
			}
		}
		//同步成功，返回聚金豆
		if(status==0) {
			map.put("status", true);//是否是当日第一次赠送  false:否，true：是
			map.put("amount", num);//当次赠送的聚金豆数量
		}


		return map;
	}


	//更具用户id查询个人中心信息
	public TbLevelInfo queryTbLevelInfo(int userId) {
		logger.info(String.format("查询个人中心信息:userId{}", userId));
		TbLevelInfo levelInfo =levelInfoDao.selectLevelInfoByUserId(userId);
		if(levelInfo!=null) {
			ScoreResult result =scoreClient.getScore(userId);
			logger.info(String.format("积分查询:result{}", result));
			if(result!=null && result.getSroce()!=null) {
				levelInfo.setRealJvjindou(result.getSroce().getAmount());
			}else {
				levelInfo.setRealJvjindou(0);
			}
			
		}else {
			levelInfo = new TbLevelInfo();
			levelInfo.setUserid(userId);
			levelInfo.setGrowthPoint(0);
			levelInfo.setGrade(Constant.GOLD);
			levelInfoDao.insertSelective(levelInfo);
		}
		return levelInfo;
	}
	
	//聚分享平台同步聚金豆(增加)
	public StringResult addlevelInfo(int userid,int integral,String orderId,int amont) {
		TbLevelInfo levelInfo =levelInfoDao.selectLevelInfoByUserId(userid);
		String  levle=levelInfo.getGrade();
		Double b=0.00;
		logger.info("赠送积分:"+integral);
		if(Constant.PLATIMUM.equals(levle)) {
			 b=integral*0.05;
		}else if(Constant.BLACK.equals(levle)) {
			 b=integral*0.1;
		}else if(Constant.DIAMOND.equals(levle)) {
			 b=integral*0.15;
		}
		//integral+=b.intValue();1111111
		integral+= getInt(b);
		logger.info("赠送总积分:"+integral+",赠送积分"+b);
		StringResult results = new StringResult();
		Result result=new Result();
		result.code=1;
		results.setResult(result);
		
		if(integral>0) {
			results=scoreClient.incomeScore(userid,integral, 5, orderId);
			logger.info("积分增加:results:"+results);
		}

		if(amont>0) {
			TbLevelInfo info = levelInfoDao.selectLevelInfoByUserId(userid);
			if(info!=null) {
				logger.info("增加成长值:"+amont);
				info.setGrowthPoint((info.getGrowthPoint()+amont));
				logger.info("增加成长值:"+info.getGrowthPoint());
				levelInfoDao.updateLevelInfo(info);
			}
		}
		return results;
	}
	//聚分享平台同步聚金豆(减少)
	public StringResult lesslevelInfo(int userid,int integral,String orderId,int amont, boolean useFenxiang, int fenXiangIntegral) {
		if (useFenxiang) {
//			scoreClient.pointIncomeOrExpenses(userid, fenXiangIntegral+"");
		}
		TbLevelInfo info = levelInfoDao.selectLevelInfoByUserId(userid);
		if(info!=null) {
			info.setRealJvjindou(info.getGrowthPoint()-amont);
		}
		levelInfoDao.updateLevelInfo(info);
		StringResult results = scoreClient.reduceScore(userid, integral, 1, orderId);
		logger.info(String.format("积分减少:results{}", results));
		return results;
	}
	
	//更具用户id查询用户积分
	public Integer queryTbLevelUserAmount(int userId) {
		logger.info(String.format("查询个人中心信息:userId{}", userId));
			ScoreResult result =scoreClient.getScore(userId);
			logger.info(String.format("积分查询:result{}", result));
			if(result.getSroce()==null) {
				return 0;
			}
		return result.getSroce().getAmount();
	}
	
	
	
	
	public static int getRandomNumInTwoIntNum(int x, int y) {
		int num = 0;
		Random random = new Random();
		num = (random.nextInt(y+1-x)+x);
		return num;
	}
	
	
	public static int getInt(double number){
	    BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
	    return Integer.parseInt(bd.toString()); 
	    } 
	
	
}
