package com.jfshare.mvp.server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.dao.AppInfoDao;
import com.jfshare.mvp.server.dao.TbAppVerifySettingDao;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.model.TbAppVerifySetting;
import com.jfshare.mvp.server.model.TbAppVerifySettingExample;
import com.jfshare.mvp.server.model.TbAppVerinfo;
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
	@Autowired
	private AppInfoServer appInfoServer;
	
	public Map<String, Object> getAppVerifyProducts() {
		TbAppVerifySettingExample tbAppVerifySettingExample = new TbAppVerifySettingExample();
		List<TbAppVerifySetting> tbAppVerifySettings = tbAppVerifySettingDao.selectByExample(tbAppVerifySettingExample);
		if (CollectionUtils.isEmpty(tbAppVerifySettings)) {
			return null;
		}
		final String products = tbAppVerifySettings.get(0).getProductNoList();
		List<String> productList = products.contains(",") ? Arrays.asList(products.split(",")) : Arrays.asList(products);
		TbProductExample tbProductExample = new TbProductExample();
		tbProductExample.createCriteria()
						.andProductIdIn(productList);
		List<TbProduct> resultProduct = tbProductDao.selectByExample(tbProductExample);
		List<Map<String, Object>> resMap = new ArrayList<Map<String,Object>>();
		for (TbProduct tbProduct : resultProduct) {
			Map<String, Object> map = new HashMap<>();
			map.put("productId", tbProduct.getProductId());
			map.put("curPrice", tbProduct.getCurPrice());
			map.put("productName", tbProduct.getProductName());
			map.put("imgKey", tbProduct.getImgKey().contains(",") ? tbProduct.getImgKey().split(",")[0] : tbProduct.getImgKey());
			resMap.add(map);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("appVersion", tbAppVerifySettings.get(0).getAppVersion());
		result.put("state", tbAppVerifySettings.get(0).getState());
		result.put("productNoList", resMap);
		return result;
	}
	
	public ResultConstant saveAppVerifyProducts(TbAppVerifySetting tbAppVerifySetting) {
		if (StringUtils.isEmpty(tbAppVerifySetting.getAppVersion())) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数错误，版本号为空");
		}
		if (StringUtils.isEmpty(tbAppVerifySetting.getProductNoList())) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数错误，商品为空");
		}
		try {
			TbAppVerinfo tbAppVerinfo = appInfoServer.getCurAppVersion(3);
			String[] oriVerStrs = tbAppVerinfo.getVersion().split(".");
			String[] newVerStrs = tbAppVerifySetting.getAppVersion().split(".");
			if (oriVerStrs.length != newVerStrs.length) {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数错误，版本号格式错误");
			}
			for (int i = 0; i < oriVerStrs.length; i++) {
				int newVer = Integer.valueOf(newVerStrs[i]);
				int oriVer = Integer.valueOf(oriVerStrs[i]);
				if (newVer <= oriVer) {
					return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数错误，版本号格式错误");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数错误，版本号格式错误");
		}
		tbAppVerifySettingDao.deleteByExample(new TbAppVerifySettingExample());
		tbAppVerifySettingDao.insert(tbAppVerifySetting);
		return ResultConstant.ofSuccess();
	}
}
