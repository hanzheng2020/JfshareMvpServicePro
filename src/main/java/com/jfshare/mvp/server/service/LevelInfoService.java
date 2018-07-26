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

import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.dao.JvjindouRuleDao;
import com.jfshare.mvp.server.dao.LevelInfoDao;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample.Criteria;
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
	// 消费聚金豆
	@Transactional
	public int openOrdisableJvjindou(int userId, int jvjindou) {
		logger.info(String.format("消费聚金豆:userId{} ,jvjindou{} ", userId,jvjindou));
		List<TbLevelInfo> levelInfos = levelInfoDao
				.selectJvjindouRuleByUserId(userId);
		for (TbLevelInfo tbLevelInfo : levelInfos) {
			tbLevelInfo.setRealJvjindou(tbLevelInfo.getRealJvjindou()
					- jvjindou);
			tbLevelInfo.setUpdateTime(new Date());
			tbLevelInfo.setRemark("消费聚金豆" + jvjindou);
			levelInfoDao.updateLevelInfoById(tbLevelInfo);
		}
		return 0;
	}
	// 查询聚金豆
	public TbLevelInfo selectByuserid(int userid) {
		logger.info(String.format("查询聚金豆:userId{}", userid));
		levelInfoDao.selectLevelInfoById(userid);
		return new TbLevelInfo();
	}
	// 积分同步赠送聚金豆
	@Transactional
	public void presentJvjindouByuserId(Integer userId) {
		logger.info(String.format("积分同步赠送聚金豆:userId{}", userId));
		List<TbLevelInfo> levelInfos = levelInfoDao.selectJvjindouRuleByUserId(userId);
		// 判断当前用户是否有聚金豆， 没有则进行首次添加,有则进行判断今天是否查询过 查询过直接return 没有则按照定义赠送的规则添加聚金豆
		// 查询聚金豆赠送规则
		TbJvjindouRuleExample example = new TbJvjindouRuleExample();
		List<TbJvjindouRule> jvjindouRules = jvjindouRuleDao.selectByExample(example);
		TbJvjindouRule jvjindouRule = null;
		if (jvjindouRules.size() == 1) {
			jvjindouRule = jvjindouRules.get(0);
		} else {
			return;
		}
		String givingRule = jvjindouRule.getGivingRule();
		if (levelInfos != null && levelInfos.size() > 0) {
			TbLevelInfo levlInfo = levelInfos.get(0);
			Date date = new Date();
			String updateTime = sdf.format(levlInfo.getUpdateTime());
			String curentTime = sdf.format(date);
			if (updateTime == curentTime) {
				return;
			} else {
				if (givingRule.equals(Constant.FIXED_PATTERN)) {
					levlInfo.setUserid(userId);
					levlInfo.setRealJvjindou(jvjindouRule.getFixedGiving()+levlInfo.getRealJvjindou());
					levlInfo.setUpdateTime(new Date());
					levlInfo.setId(levlInfo.getId());
					levlInfo.setRemark("修改赠送固定模式");
					levelInfoDao.updateLevelInfoById(levlInfo);
				} else if (givingRule.equals(Constant.RANDOUM_PATTERN)) {
					int randomGivingMin = jvjindouRule.getRandomGivingMin();
					int randomGivingMax = jvjindouRule.getRandomGivingMax();
					int num = getRandomNumInTwoIntNum(randomGivingMin,
							randomGivingMax);
					levlInfo.setUserid(userId);
					levlInfo.setRealJvjindou(num+levlInfo.getRealJvjindou());
					levlInfo.setUpdateTime(new Date());
					levlInfo.setRemark("修改赠送随机模式");
					levlInfo.setId(levlInfo.getId());
					levelInfoDao.updateLevelInfoById(levlInfo);
				}
			}
		} else {
			TbLevelInfo info = new TbLevelInfo();
			if (givingRule.equals(Constant.FIXED_PATTERN)) {
				info.setCreateTime(new Date());
				info.setUserid(userId);
				info.setRealJvjindou(jvjindouRule.getFixedGiving());
				info.setUpdateTime(new Date());
				info.setRemark("首次增加固定模式");
				levelInfoDao.insertSelective(info);
			} else if (givingRule.equals(Constant.RANDOUM_PATTERN)) {
				int randomGivingMin = jvjindouRule.getRandomGivingMin();
				int randomGivingMax = jvjindouRule.getRandomGivingMax();
				int num = getRandomNumInTwoIntNum(randomGivingMin,
						randomGivingMax);
				info.setCreateTime(new Date());
				info.setUserid(userId);
				info.setRealJvjindou(num);
				info.setUpdateTime(new Date());
				info.setRemark("首次增加随机模式");
				levelInfoDao.insertSelective(info);
			}
		}
	}

	public static int getRandomNumInTwoIntNum(int x, int y) {
		int num = 0;
		Random random = new Random();
		int cha = Math.abs(x - y);
		if (cha <= 1) {
			logger.info("两个数字之间没有整数了!");
			return 1;
		} else {
			int randomCha = random.nextInt(cha) + 1;
			if (randomCha >= cha) {
				randomCha = cha - 1;
			}
			if (x > y) {
				logger.info("x>y时，它们之间的随机整数为：" + (randomCha + y));
				num = randomCha + y;
			}
			if (x < y) {
				logger.info("x<y时，它们之间的随机整数为：" + (randomCha + x));
				num = randomCha + x;
			}
		}
		return num;
	}
    //用户首次注册
	public void userFirstRegister(int userId) {
		logger.info(String.format("用户首次注册:userId{}", userId));
		List<TbLevelInfo> levelInfos = levelInfoDao.selectJvjindouRuleByUserId(userId);
		if(levelInfos.size()>0){
			return;
		}else{
		    TbLevelInfo info=new TbLevelInfo();
		    info.setCreateTime(new Date());
		    info.setLevle(Constant.DEFAULE_LEVEL);//默认等级为1级
		    info.setUserid(userId);
		    info.setUpdateTime(new Date());
		    info.setRemark("首次注册200成长点！");
		    levelInfoDao.insertSelective(info);
		}
	}

}
