package com.jfshare.mvp.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.controller.ProductController;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.dao.TbProductItemDao;
import com.jfshare.mvp.server.mapper.TbProductDetailMapper;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductDetailExample;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;
import com.jfshare.mvp.server.utils.DateUtils;
import com.jfshare.mvp.server.utils.FileOpUtil;

@Service
public class ProductService {
	private final static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private TbProductDao tbProductDao;
	@Autowired
	private TbProductDetailMapper tbProductDetailMapper;
	@Autowired
	private TbProductItemDao tbProductItemDao;

	//根据条件搜索商品信息
	public List<TbProductSurvey> productSurveyQuery(String param,Integer itemNo,Integer activeState, Integer curpage,
			Integer percount) {
		ProductSurveyQueryParam productParam = new ProductSurveyQueryParam();
		if (!StringUtils.isEmpty(param)) {
			productParam.setParam(param);
		}
		if(itemNo >= 0) {
			productParam.setItemNo(itemNo);
		}
		if(activeState >= 0) {
			productParam.setActiveState(activeState);
		}
		PageHelper.startPage(curpage, percount);
		//处理图片  列表只返回一张图片
		List<TbProductSurvey> productSurveyQuery = tbProductDao.productSurveyQuery(productParam);
		for (TbProductSurvey tbProductSurvey : productSurveyQuery) {
			String imgKey = tbProductSurvey.getImgKey();
			if(!StringUtils.isEmpty(imgKey) && imgKey.contains(",")) {
				tbProductSurvey.setImgKey(imgKey.split(",")[0]);
			}
		}	
		return productSurveyQuery;
	}

	//新增商品
	public int addProduct(Product product) {
		TbProductWithBLOBs tbProductWithBLOBs = new TbProductWithBLOBs();
		tbProductWithBLOBs.setProductId(product.getProductId());
		tbProductWithBLOBs.setProductName(product.getProductName());
		tbProductWithBLOBs.setItemNo(product.getItemNo());
		tbProductWithBLOBs.setProductHeader(product.getProductHeader());
		tbProductWithBLOBs.setCurPrice(product.getCurPrice());
		tbProductWithBLOBs.setOrgPrice(product.getOrgPrice());
		tbProductWithBLOBs.setPresentexp(product.getPresentexp());
		tbProductWithBLOBs.setProductStock(product.getProductStock());
		tbProductWithBLOBs.setActiveState(product.getActiveState());
		tbProductWithBLOBs.setImgKey(product.getImgKey());
		tbProductWithBLOBs.setCreateTime(new Date());
		// 商品使用说明和商品兑换说明使用product_detail表更新
		TbProductDetail tbProductDetail = new TbProductDetail();
		tbProductDetail.setDetailKey(product.getProductId());
		tbProductDetail.setProductInstructions(product.getProductInstructions());
		tbProductDetail.setProductExchange(product.getProductExchange());
		tbProductDetail.setCreateTime(new Date());
		//tbProductDetail.setUpdateTime(new Date());
		int count = tbProductDetailMapper.insert(tbProductDetail);
		int result = 0;
		if (count > 0) {
			result = tbProductDao.addProduct(tbProductWithBLOBs);
		}
		return result;
	}

	//删除商品
	public int deleteProduct(String productId) {
		int result = 0;
		if (!StringUtils.isEmpty(productId)) {
			TbProductDetailExample example = new TbProductDetailExample();
			example.createCriteria().andDetailKeyEqualTo(productId);
			int count = tbProductDetailMapper.deleteByExample(example);
			if (count > 0) {
				result = tbProductDao.deleteProduct(productId);
			}
		}
		return result;
	}

	//更新商品
	public int updateProduct(Product product) {
		TbProductWithBLOBs tbProductWithBLOBs = new TbProductWithBLOBs();
		tbProductWithBLOBs.setProductId(product.getProductId());
		tbProductWithBLOBs.setProductName(product.getProductName());
		tbProductWithBLOBs.setItemNo(product.getItemNo());
		tbProductWithBLOBs.setProductHeader(product.getProductHeader());
		tbProductWithBLOBs.setCurPrice(product.getCurPrice());
		tbProductWithBLOBs.setOrgPrice(product.getOrgPrice());
		tbProductWithBLOBs.setPresentexp(product.getPresentexp());
		tbProductWithBLOBs.setProductStock(product.getProductStock());
		Integer activeState = product.getActiveState();
		tbProductWithBLOBs.setActiveState(activeState);
		if(activeState == Constant.PRODUCT_STATE_ONSELL) {
			tbProductWithBLOBs.setLastPutawayTime(new Date());
		}else {
			tbProductWithBLOBs.setLastSoldoutTime(new Date());
		}
		tbProductWithBLOBs.setImgKey(product.getImgKey());
		// 更新商品详情表
		TbProductDetail tbProductDetail = new TbProductDetail();
		tbProductDetail.setDetailKey(product.getProductId());
		tbProductDetail.setProductInstructions(product.getProductInstructions());
		tbProductDetail.setProductExchange(product.getProductExchange());
		// tbProductDetail.setCreateTime(new Date());
		tbProductDetail.setUpdateTime(new Date());
		TbProductDetailExample example = new TbProductDetailExample();
		example.createCriteria().andDetailKeyEqualTo(product.getProductId());
		int count = tbProductDetailMapper.updateByExampleSelective(tbProductDetail, example);
		int result = 0;
		if (count > 0) {
			result = tbProductDao.updateProduct(tbProductWithBLOBs);
		}
		return result;
	}
	
	//商品导出execl表格
	public String exportProduct(String param,Integer itemNo,Integer activeState, Integer curpage,
			Integer percount) {
		 String path = "";
		 try {
			List<TbProductSurvey> productList = productSurveyQuery(param,itemNo,activeState,curpage,percount);
			 if(productList != null && productList.size() > 0) {
				 byte[] files = FileOpUtil.getExportProduct(productList);
				 path = FileOpUtil.uploadFile(files, DateUtils.dateToStr(new Date(), Constant.FORMAT_DEFAULT_MIN) + ".xls");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return path;
	}
	
	/**
	 * 
	 * 根据商品id来搜索商品
	 * @param productId
	 * @return
	 */
	public TbProduct getProductOne(String productId) {
		TbProductExample example = new TbProductExample();
		example.createCriteria().andProductIdEqualTo(productId);
		List<TbProduct> products =  tbProductDao.selectByExample(example);
		if(products!=null && products.size()>0) {
			return products.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 * 根据类目id来搜索商品
	 * @param productId
	 * @return
	 */
	public List<TbProductSurvey> queryProductByItemNo(Integer itemNo) {
		List<TbProductSurvey> productList = new ArrayList<TbProductSurvey>();
		List<TbProductItem> tbProductItems = tbProductItemDao.queryItemList(itemNo+"");
		logger.info("tbProductItems : " + tbProductItems.size());
		ProductSurveyQueryParam productParam = new ProductSurveyQueryParam();
		productParam.setActiveState(0);
		productParam.setActiveState(200);//只查询已上架的商品
		if(tbProductItems.size() > 0) {
			for (TbProductItem tbProductItem : tbProductItems) {
				productParam.setItemNo(Integer.parseInt(tbProductItem.getItemNo()));
				List<TbProductSurvey> productSurveyQuery = tbProductDao.productSurveyQuery(productParam);
				for (TbProductSurvey tbProductSurvey : productSurveyQuery) {
					productList.add(tbProductSurvey);
				}
			}
		}
		//处理图片  列表只返回一张图片
		for (TbProductSurvey tbProductSurvey : productList) {
			String imgKey = tbProductSurvey.getImgKey();
			if(!StringUtils.isEmpty(imgKey) && imgKey.contains(",")) {
				tbProductSurvey.setImgKey(imgKey.split(",")[0]);
			}
		}	
		return productList;
	}
}
