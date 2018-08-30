package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbProductDetailMapper;
import com.jfshare.mvp.server.model.TbProductDetailExample;
import com.jfshare.mvp.server.model.TbProductDetailWithBLOBs;

@Repository
public class ProductDetailDao {

	@Autowired
	private TbProductDetailMapper productDetailMapper;
	
	
	
	public List<TbProductDetailWithBLOBs> selectByExample(TbProductDetailExample example){
		return productDetailMapper.selectByExampleWithBLOBs(example);
	}
	
	
}
