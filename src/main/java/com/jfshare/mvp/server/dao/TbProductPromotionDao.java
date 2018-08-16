package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbProductPromotionMapper;
import com.jfshare.mvp.server.model.TbProductPromotion;
import com.jfshare.mvp.server.model.TbProductPromotionExample;

/**
 * @author fengxiang
 * @date 2018-07-23
 */
@Repository
public class TbProductPromotionDao {
	@Autowired
	private TbProductPromotionMapper tbProductPromotionMapper;
	
	public List<TbProductPromotion> selectByExample(TbProductPromotionExample example) {
		return tbProductPromotionMapper.selectByExample(example);
	}
	
	public int updateByPrimaryKey(TbProductPromotion record) {
		record.setUpdateTime(new Date());
		return tbProductPromotionMapper.updateByPrimaryKey(record);
	}
	
	public int updateByExampleSelective(TbProductPromotion record, TbProductPromotionExample example) {
		record.setUpdateTime(new Date());
		return tbProductPromotionMapper.updateByExampleSelective(record, example);
	}
	
	public int deleteByExample(TbProductPromotionExample example) {
		return tbProductPromotionMapper.deleteByExample(example);
	}

	public int insert(TbProductPromotion record) {
		record.setUpdateTime(new Date());
		record.setCreateTime(new Date());
    	return tbProductPromotionMapper.insert(record);
    }
}
