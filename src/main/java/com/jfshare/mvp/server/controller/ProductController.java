package com.jfshare.mvp.server.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.service.ProductService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;

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
	@Autowired
	private ProductService productService;

	@ApiOperation(value = "根据商品id name 获取商品信息", notes = "根据商品id name 获取商品信息")
	@PostMapping("/productSurveyQuery")
	public ResultConstant productSurveyQuery(@RequestParam(value = "productId", required=false) String productId,
			@RequestParam(value = "productName", required=false) String productName) {
		List<TbProductSurvey> productList = productService.productSurveyQuery(productId, productName);
		if(!CollectionUtils.isEmpty(productList)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanToMap(productList));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品信息失败");
	}
}
