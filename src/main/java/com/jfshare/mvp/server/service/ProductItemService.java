package com.jfshare.mvp.server.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.dao.TbProductItemDao;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
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
	
	@Autowired
	private TbProductDao tbProductDao;
	
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
	
	public boolean addProductItem(String itemName, String itemDesc, String parentItemNo) {
		
		return false;
	}

	public List<TbProductItem> getProductItem(String itemName, boolean useLike) {
		if (!useLike) {
			return getProductItem(itemName);
		}
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		if (!StringUtils.isEmpty(itemName)) {
			tbProductItemExample.createCriteria()
								.andItemNameLike("%"+itemName+"%");
		}
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		return tbProductItems;
	}
	
	public List<TbProductItem> getProductItem(String itemName) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		if (!StringUtils.isEmpty(itemName)) {
			tbProductItemExample.createCriteria()
								.andItemNameEqualTo(itemName);
		}
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		return tbProductItems;
	}
	
	public ResultConstant deleteProductItem(String itemNo) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		if (!StringUtils.isEmpty(itemNo)) {
			tbProductItemExample.createCriteria()
								.andParentItemNoEqualTo(itemNo);
		}
		List<TbProductItem> sonProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		if (!CollectionUtils.isEmpty(sonProductItems)) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "存在子节点，请先删除子节点！");
		}
		TbProductExample tbProductExample = new TbProductExample();
		tbProductExample.createCriteria()
						.andItemNoEqualTo(0);
		List<TbProduct> tbProducts = tbProductDao.selectByExample(tbProductExample);
		if (!CollectionUtils.isEmpty(tbProducts)) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "该类目中存在商品！");
		}
		tbProductItemExample.clear();
		if (!StringUtils.isEmpty(itemNo)) {
			tbProductItemExample.createCriteria()
								.andParentItemNoEqualTo(itemNo);
		}
		tbProductItemDao.deleteByExample(tbProductItemExample);
		return ResultConstant.ofSuccess();
	}
}