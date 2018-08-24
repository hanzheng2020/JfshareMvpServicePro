package com.jfshare.mvp.server.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.finagle.server.ProductClient;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.service.ProductDetailService;
import com.jfshare.mvp.server.service.ProductService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;
import com.jfshare.mvp.server.service.PromotionSettingService;
import com.jfshare.mvp.server.task.ProductScheduler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author fengxiang
 * @date 2018-07-23
 */
@Api(value = "api", tags = "商品模块相关API")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@Autowired
	private ProductClient productClient;
	
	@ApiOperation(value = "根据商品id name 获取商品信息", notes = "根据商品id name 获取商品信息  param:商品名称或者商品id   itemNo:类目id activeState：商品状态:100 待上架  200 已上架 300 已下架   itemNo activeState为必传参数 默认传0")
	@PostMapping("/productSurveyQuery")
	public ResultConstant productSurveyQuery(@RequestParam(value = "param", required = false) String param,
			@RequestParam(value = "itemNo", required = false) Integer itemNo,
			@RequestParam(value = "activeState", required = false) Integer activeState,
			@RequestParam(value = "curpage", required = true) Integer curpage,
			@RequestParam(value = "percount", required = true) Integer percount) {
		logger.info("param"+param+" itemNo:"+itemNo+" activeState:"+activeState+" curpage:"+curpage+" percount:"+percount);
		List<TbProductSurvey> productList;
		try {
			productList = productService.productSurveyQuery(param, itemNo,
					activeState, curpage, percount);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品信息失败");
		}
		PageInfo<TbProductSurvey> pageInfo = new PageInfo<>(productList);
		return ResultConstant.ofSuccess(pageInfo);
		  //return ResultConstant.ofSuccess(productList);
	}

	@ApiOperation(value = "新增商品", notes = "新增商品信息")
	@PostMapping("/addProduct")
	public ResultConstant addProduct(Product product) {
		int result = productService.addProduct(product);
		if (result > 0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "新增商品失败");
	}

	@ApiOperation(value = "删除商品", notes = "删除商品信息")
	@PostMapping("/deleteProduct")
	public ResultConstant deleteProduct(@RequestParam(value = "productId", required = false) String productId) {
		int result;
		try {
			result = productService.deleteProduct(productId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "删除商品失败");
		}
		if(result > 0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "删除商品失败");
	}

	@ApiOperation(value = "更新商品", notes = "更新商品信息")
	@PostMapping("/updateProduct")
	public ResultConstant updateProduct(Product product) {
		int result = productService.updateProduct(product);
		if (result > 0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新商品失败");
	}

	@ApiOperation(value = "商品导出execl", notes = "商品导出execl表格")
	@PostMapping("/exportProduct")
	public ResultConstant exportProduct(@RequestParam(value = "param", required = false) String param,
			@RequestParam(value = "itemNo", required = false) Integer itemNo,
			@RequestParam(value = "activeState", required = false) Integer activeState,
			@RequestParam(value = "curpage", required = true) Integer curpage,
			@RequestParam(value = "percount", required = true) Integer percount) {
		String path = productService.exportProduct(param, itemNo, activeState, curpage, percount);
		if (!StringUtils.isEmpty(path)) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "导出商品 execl失败");
	}
	
	@ApiOperation(value = "查询商品详情", notes = "根据商品ID查询商品详情，productId")
	@PostMapping("/queryProductInfo")
	public ResultConstant exportProduct(@RequestParam(value = "productId", required = true) String productId) {
		
		TbProduct  product =  productService.getProductOne(productId);

		if(product!=null) {
			List<TbProductDetail> productDetails =  productDetailService.selectByExample(productId);
			Map productMap = ConvertBeanToMapUtils.convertBeanToMap(product, "");
			if(productDetails!=null&&productDetails.size()>0) {
				TbProductDetail productDetail = productDetails.get(0);
				productMap.put("productDetail", productDetail.getProductDetail());//商品详情
				productMap.put("productInstructions", productDetail.getProductInstructions());//商品使用说明
				productMap.put("productExchange", productDetail.getProductExchange());//商品兑换说明
			}
			
			return ResultConstant.ofSuccess(productMap);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "查询商品详情失败！");
		
	}
	
	@ApiOperation(value = "查询商品详情", notes = "根据商品ID查询聚分享商品详情，productId")
	@PostMapping("/queryProduct")
	public ResultConstant queryProduct(@RequestParam(value = "productId", required = true) String productId) {
		com.jfshare.finagle.thrift.product.Product product = null;
		TbProduct tbProduct = null;
		try {
			product = productClient.getProduct(productId);
			tbProduct = ConvertBeanToMapUtils.convertBeanToMap(product);
			tbProduct.setProductStock(productClient.getProductCardByState(productId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品信息失败");
		}
		return ResultConstant.ofSuccess(tbProduct);	
	}

	@ApiOperation(value = "根据类目id 获取商品信息", notes = "根据类目id获取商品信息   默认传0   查询全部商品")
	@PostMapping("/queryproductByItemNo")
	public ResultConstant queryproductByItemNo(@RequestParam(value = "itemNo", required = false) Integer itemNo) {
		logger.info("queryproductByItemNo  itemNo: " + itemNo);
		List<TbProductSurvey> productList;
		try {
			productList = productService.queryProductByItemNo(itemNo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品信息失败");
		}
		return ResultConstant.ofSuccess(productList);
	}
}
