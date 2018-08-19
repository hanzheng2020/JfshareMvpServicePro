package com.jfshare.mvp.server.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.dao.TbProductItemDao;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductItemExample;
import com.jfshare.mvp.server.model.TbProductItemExample.Criteria;

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

	public List<Map<String, Object>> getProductItem(String itemName, boolean useLike, boolean asTree, Integer pageNum, Integer pageSize) {
		if (!useLike) {
			return getProductItem(itemName, asTree, pageNum, pageSize);
		}
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		Criteria criteria = tbProductItemExample.createCriteria();
		if (asTree) {
			criteria.andParentItemNoIsNull();
		} else {
			tbProductItemExample.setOrderByClause("create_time desc");
			PageHelper.startPage(pageNum, pageSize,true);
		}
		if (!StringUtils.isEmpty(itemName)) {
			criteria.andItemNameLike("%"+itemName+"%");
		}
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		
		List<Map<String, Object>> result = new ArrayList<>();
		for (TbProductItem tbProductItem : tbProductItems) {
			result.add(createItemTree(tbProductItem, asTree));
		}
		if (tbProductItems instanceof Page) {
			Page<Map<String, Object>> page = new Page<>();
			page.setPageNum(page.getPageNum());
			page.setPageSize(page.getPageSize());
			page.setOrderBy(page.getOrderBy());
			page.setPages(page.getPages());
			page.setTotal(page.getTotal());;
            //由于结果是>startRow的，所以实际的需要+1
            if (page.size() == 0) {
            	page.setStartRow(0);
            	page.setEndRow(0);
            } else {
            	page.setStartRow(page.getStartRow() + 1);
                //计算实际的endRow（最后一页的时候特殊）
            	page.setEndRow(page.getStartRow() - 1 + page.size());
            }
			page.addAll(result);
			return page;
		}
		return result;
	}
	
	public List<Map<String, Object>> getProductItem(String itemNo, boolean asTree, Integer pageNum, Integer pageSize) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		Criteria criteria = tbProductItemExample.createCriteria();
		if (asTree) {
			criteria.andParentItemNoIsNull();
		} else {
			tbProductItemExample.setOrderByClause("create_time desc");
			PageHelper.startPage(pageNum, pageSize,true);
		}
		if (!StringUtils.isEmpty(itemNo)) {
			criteria.andItemNoEqualTo(itemNo);
		}
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		List<Map<String, Object>> result = new ArrayList<>();
		for (TbProductItem tbProductItem : tbProductItems) {
			result.add(createItemTree(tbProductItem, asTree));
		}
		if (tbProductItems instanceof Page) {
			Page<Map<String, Object>> page = new Page<>();
			page.setPageNum(page.getPageNum());
			page.setPageSize(page.getPageSize());
			page.setOrderBy(page.getOrderBy());
			page.setPages(page.getPages());
			page.setTotal(page.getTotal());;
            //由于结果是>startRow的，所以实际的需要+1
            if (page.size() == 0) {
            	page.setStartRow(0);
            	page.setEndRow(0);
            } else {
            	page.setStartRow(page.getStartRow() + 1);
                //计算实际的endRow（最后一页的时候特殊）
            	page.setEndRow(page.getStartRow() - 1 + page.size());
            }
			page.addAll(result);
			return page;
		}
		return result;
	}
	
	private Map<String, Object> createItemTree(TbProductItem tbProductItem, boolean asTree) {
		Map<String, Object> rtMap = new HashMap<>();
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		if (asTree) {
			tbProductItemExample.createCriteria()
								.andParentItemNoEqualTo(tbProductItem.getItemNo());
			List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
			if (!CollectionUtils.isEmpty(tbProductItems)) {
				List<Map<String, Object>> listChild = new ArrayList<>();
				for (TbProductItem productItem : tbProductItems) {
					Map<String, Object> mapChild = new HashMap<>();
					mapChild = createItemTree(productItem, asTree);
					mapChild.put("parentItemName", tbProductItem.getItemName());
					mapChild.put("parentItemNo", tbProductItem.getItemNo());
					listChild.add(mapChild);
				}
				rtMap.put("children", listChild);
			}
		} else {
			if (StringUtils.isEmpty(tbProductItem.getParentItemNo())) {
				rtMap.put("parentItemName", "");
				rtMap.put("parentItemNo", "");
			} else {
				tbProductItemExample.createCriteria()
									.andItemNoEqualTo(tbProductItem.getParentItemNo());
				List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
				rtMap.put("parentItemName", tbProductItems.get(0).getItemName());
				rtMap.put("parentItemNo", tbProductItems.get(0).getItemNo());
			}
		}

		rtMap.put("itemNo", tbProductItem.getItemNo());
		rtMap.put("itemName", tbProductItem.getItemName());
		rtMap.put("itemDesc", tbProductItem.getItemDesc());
		return rtMap;
	}
	
	public ResultConstant deleteProductItem(List<String> itemNos) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		for (String itemNo : itemNos) {
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
		}
		
		tbProductItemExample.createCriteria()
							.andItemNoIn(itemNos);
		tbProductItemDao.deleteByExample(tbProductItemExample);
		return ResultConstant.ofSuccess();
	}
}