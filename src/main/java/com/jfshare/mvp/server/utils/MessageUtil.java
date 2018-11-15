package com.jfshare.mvp.server.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import net.sf.json.JSONObject;

/**
 * Created by hanzheng on 2018/7/24.
 */
public class MessageUtil {
	/**
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String localVersion) throws Exception {
		if (version1 == null || localVersion == null) {
			throw new Exception("compareVersion error:illegal params.");
		}
		String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
		String[] versionArray2 = localVersion.split("\\.");
		int idx = 0;
		int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
		int diff = 0;
		while (idx < minLength
				&& (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
				&& (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
			++idx;
		}
		//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}
	
	public static void main(String[] args) {
//		try {
//			int i = compareVersion("2.7.5.0124", "2.7.4.0124");
//			System.out.println(i);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//int orderAmount = Integer.valueOf(null);
		
  
			  String[] arrayA = new String[] { "1", "2", "3", "3", "4", "5" };   
			  String[] arrayB = new String[] { "3", "4", "4", "5", "6", "7","8" };   
			     
			  List<String> a = Arrays.asList(arrayA);   
			  List<String> b = Arrays.asList(arrayB);   
			  //并集   
			  Collection<String> union = CollectionUtils.union(a, b);   
			  //交集   
			  Collection<String> intersection = CollectionUtils.intersection(a, b);   
			  //交集的补集   
			  Collection<String> disjunction = CollectionUtils.disjunction(a, b);   
			  //集合相减   
			  Collection<String> subtract = CollectionUtils.subtract(a, b);   
			     
			  Collections.sort((List<String>) union);   
			  Collections.sort((List<String>) intersection);   
			  Collections.sort((List<String>) disjunction);   
			  Collections.sort((List<String>) subtract);   
			     
			  System.out.println("A: " + ArrayUtils.toString(a.toArray()));   
			  System.out.println("B: " + ArrayUtils.toString(b.toArray()));   
			  System.out.println("--------------------------------------------");   
			  System.out.println("Union(A, B): " + ArrayUtils.toString(union.toArray()));   
			  System.out.println("Intersection(A, B): " + ArrayUtils.toString(intersection.toArray()));   
			  System.out.println("Disjunction(A, B): " + ArrayUtils.toString(disjunction.toArray()));   
			  System.out.println("Subtract(A, B): " + ArrayUtils.toString(subtract.toArray()));   
	}
	
}
