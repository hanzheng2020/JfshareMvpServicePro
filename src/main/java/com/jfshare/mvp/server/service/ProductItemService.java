package com.jfshare.mvp.server.service;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.dao.TbProductItemDao;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductItemExample;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
	private SequenceService sequenceService;
	
	public boolean updateProductItem(String itemNo, String itemName, String itemDesc) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		tbProductItemExample.createCriteria()
							.andItemNoEqualTo(itemNo);
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		
		if (CollectionUtils.isEmpty(tbProductItems)) {
			return false;
		}
		TbProductItem tbProductItemt = tbProductItems.get(0);
		if (!StringUtils.isEmpty(itemName)) {
			tbProductItemt.setItemName(itemName);
		}
		if (!StringUtils.isEmpty(itemDesc)) {
			tbProductItemt.setItemDesc(itemDesc);
		}
		tbProductItemDao.updateByPrimaryKey(tbProductItemt);
		return true;
	}
	
	public boolean addProductItem(String itemName, String itemDesc, String parentItemNo) {
		TbProductItem tbProductItem = new TbProductItem();
		tbProductItem.setItemDesc(itemDesc);
		tbProductItem.setItemName(itemName);
		tbProductItem.setParentItemNo(parentItemNo);
		tbProductItem.setItemNo(sequenceService.generalItemNo());
		tbProductItemDao.insert(tbProductItem);
		return true;
	}

	public List<Map<String, Object>> getProductItem(String itemName, boolean useLike) {
		if (!useLike) {
			return getProductItem(itemName);
		}
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		if (!StringUtils.isEmpty(itemName)) {
			tbProductItemExample.createCriteria()
								.andItemNameLike("%"+itemName+"%")
								.andParentItemNoIsNull();
		}
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		List<Map<String, Object>> result = new ArrayList<>();
		for (TbProductItem tbProductItem : tbProductItems) {
			result.add(createItemTree(tbProductItem));
		}
		return result;
	}
	
	public List<Map<String, Object>> getProductItem(String itemNo) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		if (!StringUtils.isEmpty(itemNo)) {
			tbProductItemExample.createCriteria()
								.andItemNoEqualTo(itemNo)
								.andParentItemNoIsNull();
		}
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		List<Map<String, Object>> result = new ArrayList<>();
		for (TbProductItem tbProductItem : tbProductItems) {
			result.add(createItemTree(tbProductItem));
		}
		return result;
	}
	
	private Map<String, Object> createItemTree(TbProductItem tbProductItem) {
		Map<String, Object> rtMap = new HashMap<>();
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		tbProductItemExample.createCriteria()
							.andParentItemNoEqualTo(tbProductItem.getItemNo());
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		if (!CollectionUtils.isEmpty(tbProductItems)) {
			List<Map<String, Object>> listChild = new ArrayList<>();
			for (TbProductItem productItem : tbProductItems) {
				listChild.add(createItemTree(productItem));
			}
			rtMap.put("children", listChild);
		}
		rtMap.put("itemNo", tbProductItem.getItemNo());
		rtMap.put("itemName", tbProductItem.getItemName());
		rtMap.put("itemDesc", tbProductItem.getItemDesc());
		return rtMap;
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
								.andItemNoEqualTo(itemNo);
		}
		tbProductItemDao.deleteByExample(tbProductItemExample);
		return ResultConstant.ofSuccess();
	}
}