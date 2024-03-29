package com.jfshare.mvp.server.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.model.TbProduct;

/**
 * 将Java bean转换为Map输出
 * @author fengxiang
 * @date 2018-07-20
 */
public class ConvertBeanToMapUtils {
	
	/**
	 * 将Java 对象转换为Map输出
	 * @param bean 需要转换的对象
	 * @param excludeFields 需要排除的字段
	 * @return
	 */
	public static Map<String, Object> convertBeanToMap(Object bean, String... excludeFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field f : bean.getClass().getDeclaredFields()) {
			String fieldName = f.getName();
			if (ArrayUtils.contains(excludeFields, fieldName) || StringUtils.equals(fieldName, "id")) {
				continue;
			}
			try {
				f.setAccessible(true);
				map.put(fieldName, f.get(bean));
				f.setAccessible(false);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public static List<Map<String, Object>> convertBeanListToMap(List<?> beanList, String... excludeFields) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		beanList.forEach(bean -> list.add(convertBeanToMap(bean, excludeFields)));
		return list;
	}
	
	public static TbProduct convertBeanToMap(com.jfshare.finagle.thrift.product.Product product) {
		TbProduct tbProduct = new TbProduct();
		tbProduct.setItemNo(product.getSubjectId());
		tbProduct.setProductId(product.getProductId());
		tbProduct.setProductName(product.getProductName());
		tbProduct.setSellerId(product.getSellerId());
		tbProduct.setProductHeader("");
		tbProduct.setCurPrice(product.getProductSku().getMinCurPrice());
		tbProduct.setOrgPrice(product.getProductSku().getMinOrgPrice());
		tbProduct.setActiveState(Constant.PRODUCT_SOLT_OUT);
		tbProduct.setPresentexp(0);
		StringBuilder sb = new StringBuilder();
		if(product.getImgKey().contains(",")) {
			String[] str = product.getImgKey().split(",");
			for(int i = 0;i < str.length;i ++) {
				if(!StringUtils.isEmpty(str[i])) {
					sb.append(str[i]).append(",");
				}
			}
			String strImg = sb.toString().substring(0, sb.toString().length() - 1);
			tbProduct.setImgKey(strImg);
		}else {
			tbProduct.setImgKey(product.getImgKey());
		}

		return tbProduct;
	}
}
