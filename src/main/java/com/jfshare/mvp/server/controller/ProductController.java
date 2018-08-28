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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jfshare.finagle.thrift.product.ProductCard;
import com.jfshare.finagle.thrift.product.ProductCardParam;
import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.finagle.server.OrderClient;
import com.jfshare.mvp.server.finagle.server.ProductClient;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.TbLevelInfo;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductDetailWithBLOBs;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.service.ProductService;
import com.jfshare.mvp.server.service.LevelInfoService;
import com.jfshare.mvp.server.service.ProductDetailService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.expr.NewArray;

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
	@Autowired
	private OrderClient orderClient;
	@Autowired
	private LevelInfoService levelInfoService;
	
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
		TbProduct productOne = productService.getProductOne(product.getProductId());
		if(productOne != null) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "新增商品重复，请重新添加！");
		}
		int result = productService.addProduct(product);
		if (result > 0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "新增商品失败");
	}

	@ApiOperation(value = "删除商品", notes = "删除商品信息")
	@PostMapping("/deleteProduct")
	public ResultConstant deleteProduct(@RequestParam(value = "productId", required = false) String productId) {
		logger.info("deleteProduct productId:" + productId);
		TbProduct productOne = productService.getProductOne(productId);
		if(productOne.getActiveState() == Constant.PRODUCT_STATE_ONSELL) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "已上架的商品不能被删除！");
		}
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
			@RequestParam(value = "activeState", required = false) Integer activeState) {
		String path = productService.exportProduct(param, itemNo, activeState);
		if (!StringUtils.isEmpty(path)) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "导出商品 execl失败");
	}
	
	@ApiOperation(value = "查询商品mvp详情", notes = "根据商品ID查询商品详情，productId")
	@PostMapping("/queryProductInfo")
	public ResultConstant exportProduct(@RequestParam(value = "productId", required = true) String productId,
			@RequestParam(value = "userId",required=false) Integer userId
			) {
		
		TbProduct  product =  productService.getProductOne(productId);

		if(product!=null) {
			int presentexp=0;
			String vipLevle="黄金会员";
			List<TbProductDetailWithBLOBs> productDetails =  productDetailService.selectByExample(productId);
			Map productMap = ConvertBeanToMapUtils.convertBeanToMap(product, "");
			if(productDetails!=null&&productDetails.size()>0) {
				TbProductDetailWithBLOBs productDetail = productDetails.get(0);
				if(product.getPresentexp()!=null) {
					presentexp=product.getPresentexp();
				}
				
				productMap.put("productDetail", productDetail.getProductDetail());//商品详情
				productMap.put("productInstructions", productDetail.getProductInstructions());//商品使用说明
				productMap.put("productExchange", productDetail.getProductExchange());//商品兑换说明
			}
			if(userId==null) {
				userId=0;
			}
			TbLevelInfo levelInfo  = levelInfoService.queryTbLevelInfo(userId);
			if(levelInfo!=null) {
				String  levle=levelInfo.getGrade();
				if(Constant.PLATIMUM.equals(levle)) {
					presentexp=new Double(presentexp*0.05).intValue();
					vipLevle="白金会员";
				}else if(Constant.BLACK.equals(levle)) {
					presentexp=new Double(presentexp*0.1).intValue();
					vipLevle="黑金会员";
				}else if(Constant.DIAMOND.equals(levle)) {
					presentexp=new Double(presentexp*0.15).intValue();
					vipLevle="钻石会员";
				}else {
					presentexp=0;
				}
			}else {
				presentexp=0;
			}
			productMap.put("levle", vipLevle);
			productMap.put("sendPresentexp", presentexp);
			
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
	
	@ApiOperation(value = "修改商品状态", notes = "设置商品状态  上架  下架")
	@PostMapping("/changeProductState")
	public ResultConstant changeProductState(@RequestParam(value = "productId", required = true) String productId,@RequestParam(value = "activeState", required = true) Integer activeState) {
		logger.info("changeProductState  productId: " + productId+",activeState:" + activeState);
		try {
			productService.changeProductState(productId, activeState);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "商品上下架失败！");
		}
		return ResultConstant.ofSuccess();
	}
	
	@ApiOperation(value = "获取商品卡券", notes = "获取商品卡券信息  orderId：订单号")
	@PostMapping("/getProductCard")
	public ResultConstant getProductCard(@RequestParam(value = "orderId", required = true) String orderId,
			@RequestParam(value = "sellerId", required = true) Integer sellerId) {
		logger.info("getProductCard  orderId: " + orderId + ", sellerId:" + sellerId);
		List<ProductCard> productCard;
		try {
			StringResult result = orderClient.sellerQueryDetail(sellerId,orderId);
			JSONObject jsonObj = JSONObject.parseObject(result.getValue());
			ProductCardParam param = new ProductCardParam();
			param.setProductId(jsonObj.getString("productId"));
			param.setSkuNum(jsonObj.getString("skuNum"));
			param.setNum(jsonObj.getInteger("count"));
			param.setTransactionId(jsonObj.getString("orderId"));
			param.setBuyerId(jsonObj.getInteger("userId"));
			productCard = productClient.getProductCard(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品卡券失败!!");
		}
		return ResultConstant.ofSuccess(productCard);
	}
}
