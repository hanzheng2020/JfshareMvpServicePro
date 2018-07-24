package com.jfshare.mvp.server.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.TbProductItemDao;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductItemExample;

/**
 * @author fengxiang
 * @date 2018-07-23
 */
@Service
public class ProductItemService {
	@Autowired
	private TbProductItemDao tbProductItemDao;
	
	public boolean updateProductItem(String itemNo, String itemName, String itemDesc) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		tbProductItemExample.createCriteria()
							.andItemNoEqualTo(itemNo);
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		
		if (CollectionUtils.isEmpty(tbProductItems)) {
			return false;
		}
		TbProductItem tbProductItemt = tbProductItems.get(0);
		if (StringUtils.isEmpty(itemName)) {
			tbProductItemt.setItemName(itemName);
		}
		if (StringUtils.isEmpty(itemDesc)) {
			tbProductItemt.setItemDesc(itemDesc);
		}
		tbProductItemDao.updateByPrimaryKey(tbProductItemt);
		return true;
	}
	
	public boolean addProductItem() {
		return false;
	}
	
	public List<TbProductItem> getProductItem(String itemNo) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		if (!StringUtils.isEmpty(itemNo)) {
			tbProductItemExample.createCriteria()
								.andItemNoEqualTo(itemNo);
		}
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		
		return tbProductItems;
	}
	
	public boolean deleteProductItem() {
		return false;
	}
}