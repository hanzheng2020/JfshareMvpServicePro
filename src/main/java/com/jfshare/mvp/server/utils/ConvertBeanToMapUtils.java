package com.jfshare.mvp.server.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 将Java bean转换为Map输出
 * @author fengxiang
 * @date 2018-07-20
 */
public class ConvertBeanToMapUtils {
	/**
	 * 将Java 对象转换为Map输出
	 * @param obj 需要转换的对象
	 * @param excludeFields 需要排除的字段
	 * @return
	 */
	public static Map<String, Object> convertBeanToMap(Object obj, String... excludeFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field f : obj.getClass().getDeclaredFields()) {
			String fieldName = f.getName();
			if (ArrayUtils.contains(excludeFields, fieldName) || StringUtils.equals(fieldName, "id")) {
				continue;
			}
			try {
				f.setAccessible(true);
				map.put(fieldName, f.get(obj));
				f.setAccessible(false);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
