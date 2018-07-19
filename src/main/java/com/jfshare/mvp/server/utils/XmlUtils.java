package com.jfshare.mvp.server.utils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;


/**
 * XML转换工具类
 * @author fengxiang
 * @date 2018-06-25
 */
public class XmlUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
	
	/**
	 * Map 转换为 XML 
	 * @param targetMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String mapToXml(Map<String, Object> targetMap) {
		logger.info("mapToXml begain: {}", JSON.toJSONString(targetMap));
		Document doc = DocumentHelper.createDocument();
		for (String name : targetMap.keySet()) {
			Element element = doc.addElement(name);
			Object value = targetMap.get(name);
			if (value instanceof Map) {
				Map<String, Object> valueMap = (Map<String, Object>) value;
				element = createElement(valueMap, element);
			}
			if (value instanceof String) {
				element.setText(value.toString());
			}
		}
		logger.info("mapToXml end: {}", doc.getRootElement().asXML());
		return doc.getRootElement().asXML();
	}
	@SuppressWarnings("unchecked")
	private static Element createElement(Map<String, Object> targetMap, Element element) {
		for (String name : targetMap.keySet()) {
			Element newElement = element.addElement(name);
			Object value = targetMap.get(name);
			if (value instanceof Map) {
				Map<String, Object> valueMap = (Map<String, Object>) value;
				newElement = createElement(valueMap, newElement);
			}
			if (value instanceof String) {
				newElement.setText(value.toString());
			}
		}
		return element;
	}
	
	/**
	 * XML转换为 Map
	 * @param xml
	 * @return
	 * @throws DocumentException 
	 */
	public static Map<String, Object> xmlToMap(String xml) throws DocumentException {
		logger.info("xmlToMap begain: {}", xml);
		Map<String, Object> reMap = new HashMap<String, Object>();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(xml.getBytes()));
		Element element = doc.getRootElement();
		reMap = createMap(element, reMap);
		logger.info("xmlToMap end: {}", JSON.toJSONString(reMap));
		return reMap;
	}
	
	private static Map<String, Object> createMap(Element element, Map<String, Object> map) {
		if (element.elements().size() > 0) {
			Map<String, Object> newMap = new HashMap<>();
			for (Object o : element.elements()) {
				Element newElement = (Element) o;
				newMap = createMap(newElement, newMap);
			}
			map.put(element.getName(), newMap);
		} else {
			map.put(element.getName(), element.getText());
		}
		return map;
	}
	
}
