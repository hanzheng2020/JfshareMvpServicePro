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
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public boolean updateProductItem(String itemNo, String itemName, String itemDesc, String parentItemNo) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		tbProductItemExample.createCriteria()
							.andItemNoEqualTo(itemNo);
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		
		if (CollectionUtils.isEmpty(tbProductItems)) {
			return false;
		}
		TbProductItem tbProductItemt = tbProductItems.get(0);
		tbProductItemt.setItemName(itemName);
		tbProductItemt.setItemDesc(itemDesc);
		tbProductItemt.setParentItemNo(StringUtils.isEmpty(parentItemNo) ? "0" : parentItemNo);
		tbProductItemDao.updateByPrimaryKey(tbProductItemt);
		return true;
	}
	
	@Transactional
	public boolean addProductItem(String itemName, String itemDesc, String parentItemNo) {
		TbProductItem tbProductItem = new TbProductItem();
		tbProductItem.setItemDesc(itemDesc);
		tbProductItem.setItemName(itemName);
		if (StringUtils.isEmpty(parentItemNo)) {
			tbProductItem.setParentItemNo("0");
		} else {
			tbProductItem.setParentItemNo(parentItemNo);
		}
		tbProductItem.setItemNo(sequenceService.generalItemNo());
		tbProductItemDao.insert(tbProductItem);
		return true;
	}

	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getProductItem(String itemName, boolean useLike, boolean asTree, Integer pageNum, Integer pageSize) {
		
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		Criteria criteria = tbProductItemExample.createCriteria();
		if (!asTree) {
			tbProductItemExample.setOrderByClause("create_time desc");
			PageHelper.startPage(pageNum, pageSize,true);
		}
		if (!StringUtils.isEmpty(itemName)) {
			if (!useLike) {
				criteria.andItemNameEqualTo(itemName);
			} else {
				criteria.andItemNameLike("%"+itemName+"%");
			}
		}
		
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		
		List<Map<String, Object>> result = new ArrayList<>();
		if (asTree) {
			for (TbProductItem tbProductItem : tbProductItems) {
				result.add(createItemTree(tbProductItem));
			}
		}
		if (!asTree && tbProductItems instanceof Page) {
			Page tbProductItemsPage = (Page) tbProductItems;
			Page<Map<String, Object>> page = new Page<>();
			page.setPageNum(tbProductItemsPage.getPageNum());
			page.setPageSize(tbProductItemsPage.getPageSize());
			page.setOrderBy(tbProductItemsPage.getOrderBy());
			page.setPages(tbProductItemsPage.getPages());
			page.setTotal(tbProductItemsPage.getTotal());;
            //由于结果是>startRow的，所以实际的需要+1
            if (page.size() == 0) {
            	page.setStartRow(0);
            	page.setEndRow(0);
            } else {
            	page.setStartRow(tbProductItemsPage.getStartRow() + 1);
                //计算实际的endRow（最后一页的时候特殊）
            	page.setEndRow(tbProductItemsPage.getStartRow() - 1 + tbProductItemsPage.size());
            }
			page.addAll(ConvertBeanToMapUtils.convertBeanListToMap(tbProductItems, "createTime", "updateTime"));
			return page;
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getProductItem(String itemNo, boolean asTree, Integer pageNum, Integer pageSize) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		Criteria criteria = tbProductItemExample.createCriteria();
		if (asTree) {
			criteria.andParentItemNoIsNull();
			List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
			List<Map<String, Object>> result = new ArrayList<>();
			for (TbProductItem tbProductItem : tbProductItems) {
				result.add(createItemTree(tbProductItem));
			}
			return result;
		} else {
			if (pageNum != null && pageSize != null) {
				PageHelper.startPage(pageNum, pageSize,true);
				List<TbProductItem> tbProductItems = tbProductItemDao.queryItemList(itemNo);
				Page tbProductItemsPage = (Page) tbProductItems;
				Page<Map<String, Object>> page = new Page<>();
				page.setPageNum(tbProductItemsPage.getPageNum());
				page.setPageSize(tbProductItemsPage.getPageSize());
				page.setOrderBy(tbProductItemsPage.getOrderBy());
				page.setPages(tbProductItemsPage.getPages());
				page.setTotal(tbProductItemsPage.getTotal());;
	            //由于结果是>startRow的，所以实际的需要+1
	            if (page.size() == 0) {
	            	page.setStartRow(0);
	            	page.setEndRow(0);
	            } else {
	            	page.setStartRow(tbProductItemsPage.getStartRow() + 1);
	                //计算实际的endRow（最后一页的时候特殊）
	            	page.setEndRow(tbProductItemsPage.getStartRow() - 1 + tbProductItemsPage.size());
	            }
	            List<Map<String, Object>> list = ConvertBeanToMapUtils.convertBeanListToMap(tbProductItems, "createTime", "updateTime");
	            Map<String, Object> remMap = null;
	            for (Map<String, Object> map : list) {
	            	if ("0".equals(map.get("itemNo"))) {
	            		remMap = map;
	            	}
	            	if (map.containsKey("parentItemNo") && map.get("parentItemNo") != null) {
						String parentItemNo = map.get("parentItemNo").toString();
						if (!StringUtils.isEmpty(parentItemNo)) {
							map.put("parentItemName", getParentItemName(parentItemNo));
						}
					}
				}
	            list.remove(remMap);
	            page.addAll(list);
				return page;
			} else {
				List<TbProductItem> tbProductItems = tbProductItemDao.queryItemList(itemNo);
				List<Map<String, Object>> list = ConvertBeanToMapUtils.convertBeanListToMap(tbProductItems, "createTime", "updateTime");
				Map<String, Object> remMap = null;
	            for (Map<String, Object> map : list) {
	            	if ("0".equals(map.get("itemNo"))) {
	            		remMap = map;
	            	}
	            	if (map.containsKey("parentItemNo") && map.get("parentItemNo") != null) {
						String parentItemNo = map.get("parentItemNo").toString();
						if (!StringUtils.isEmpty(parentItemNo)) {
							map.put("parentItemName", getParentItemName(parentItemNo));
						}
					}
				}
	            list.remove(remMap);
				return list;
			}
		}
	}
	
	private String getParentItemName(String parentItemNo) {
		TbProductItemExample tbProductItemExample = new TbProductItemExample();
		tbProductItemExample.createCriteria()
							.andItemNoEqualTo(parentItemNo);
		List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
		return tbProductItems.get(0).getItemName();
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
				Map<String, Object> mapChild = new HashMap<>();
				mapChild = createItemTree(productItem);
				mapChild.put("parentItemName", tbProductItem.getItemName());
				mapChild.put("parentItemNo", tbProductItem.getItemNo());
				listChild.add(mapChild);
			}
			rtMap.put("children", listChild);
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
							.andItemNoEqualTo(Integer.valueOf(itemNo));
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