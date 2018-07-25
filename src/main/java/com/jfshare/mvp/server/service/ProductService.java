package com.jfshare.mvp.server.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.mapper.TbProductDetailMapper;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductDetailExample;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;
import com.jfshare.mvp.server.utils.DateUtils;
import com.jfshare.mvp.server.utils.FileOpUtil;

@Service
public class ProductService {
	@Autowired
	private TbProductDao tbProductDao;
	@Autowired
	private TbProductDetailMapper tbProductDetailMapper;

	public List<TbProductSurvey> productSurveyQuery(String productId, String productName,Integer itemNo,Integer activeState, Integer curpage,
			Integer percount) {
		ProductSurveyQueryParam param = new ProductSurveyQueryParam();
		if (StringUtils.isEmpty(productId)) {
			param.setProductId(productId);
		}
		if (StringUtils.isEmpty(productName)) {
			param.setProductName(productName);
		}
		if(itemNo > 0) {
			param.setItemNo(itemNo);
		}
		if(activeState > 0) {
			param.setActiveState(activeState);
		}
		PageHelper.startPage(curpage, percount);
		return tbProductDao.productSurveyQuery(param);
	}

	public int addProduct(Product product) {
		TbProductWithBLOBs tbProductWithBLOBs = new TbProductWithBLOBs();
		tbProductWithBLOBs.setProductId(product.getProductId());
		tbProductWithBLOBs.setProductName(product.getProductName());
		tbProductWithBLOBs.setItemNo(product.getItemNo());
		tbProductWithBLOBs.setProductHeader(product.getProductHeader());
		tbProductWithBLOBs.setCurPrice(product.getCurPrice());
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
		tbProductDetail.setUpdateTime(new Date());
		int count = tbProductDetailMapper.insert(tbProductDetail);
		int result = 0;
		if (count > 0) {
			result = tbProductDao.addProduct(tbProductWithBLOBs);
		}
		return result;
	}

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

	public int updateProduct(Product product) {
		TbProductWithBLOBs tbProductWithBLOBs = new TbProductWithBLOBs();
		tbProductWithBLOBs.setProductId(product.getProductId());
		tbProductWithBLOBs.setProductName(product.getProductName());
		tbProductWithBLOBs.setItemNo(product.getItemNo());
		tbProductWithBLOBs.setProductHeader(product.getProductHeader());
		tbProductWithBLOBs.setCurPrice(product.getCurPrice());
		tbProductWithBLOBs.setPresentexp(product.getPresentexp());
		tbProductWithBLOBs.setProductStock(product.getProductStock());
		tbProductWithBLOBs.setActiveState(product.getActiveState());
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
	public String exportProduct(String productId, String productName,Integer itemNo,Integer activeState, Integer curpage,
			Integer percount) {
		 String path = "";
		 try {
			List<TbProductSurvey> productList = productSurveyQuery(productId,productName,itemNo,activeState,curpage,percount);
			 if(productList != null && productList.size() > 0) {
				 byte[] files = FileOpUtil.getExportProduct(productList);
				 path = FileOpUtil.uploadFile(files, DateUtils.dateToStr(new Date(), Constant.FORMAT_DEFAULT_MIN) + ".xls");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return path;
	}
}
