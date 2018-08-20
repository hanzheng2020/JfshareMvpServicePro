package com.jfshare.mvp.server.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.dao.TbAppVerifySettingDao;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.model.TbAppVerifySetting;
import com.jfshare.mvp.server.model.TbAppVerifySettingExample;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;

/**
 * @author fengxiang
 * @date 2018-08-20
 */
@Service
public class AppVerifySettingService {
	@Autowired
	private TbAppVerifySettingDao tbAppVerifySettingDao;
	@Autowired
	private TbProductDao tbProductDao;
	
	public List<TbProduct> getAppVerifyProducts(String appVersion) {
		TbAppVerifySettingExample tbAppVerifySettingExample = new TbAppVerifySettingExample();
		tbAppVerifySettingExample.createCriteria()
								 .andAppVersionEqualTo(appVersion)
								 .andStateEqualTo(true);
		List<TbAppVerifySetting> tbAppVerifySettings = tbAppVerifySettingDao.selectByExample(tbAppVerifySettingExample);
		if (CollectionUtils.isEmpty(tbAppVerifySettings)) {
			return null;
		}
		final String products = tbAppVerifySettings.get(0).getProductNoList();
		List<String> productList = products.contains(",") ? Arrays.asList(products.split(",")) : Arrays.asList(products);
		TbProductExample tbProductExample = new TbProductExample();
		tbProductExample.createCriteria()
						.andProductIdIn(productList);
		List<TbProduct> result = tbProductDao.selectByExample(tbProductExample);
		return result;
	}
	
	public ResultConstant saveAppVerifyProducts(TbAppVerifySetting tbAppVerifySetting) {
		if (StringUtils.isEmpty(tbAppVerifySetting.getAppVersion())) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数错误，版本号为空");
		}
		if (StringUtils.isEmpty(tbAppVerifySetting.getProductNoList())) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数错误，商品为空");
		}
		tbAppVerifySettingDao.insert(tbAppVerifySetting);
		return ResultConstant.ofSuccess();
	}
}
