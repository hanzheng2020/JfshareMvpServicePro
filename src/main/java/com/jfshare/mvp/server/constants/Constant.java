package com.jfshare.mvp.server.constants;

/**
 * 公共变量类
 */
public class Constant {
   //使用聚金豆
   public static final int  USE_JVJINDOU=1;
   //不使用聚金豆
   public static final int  DISABLED_JVJINDOU=2;
   //不使用聚金豆
   public static final int  JVJINDOU_NUM=1;
   //使用聚金豆成功
   public static final int  JVJINDOU_SUCCESS=0;
   //随机模式
   public static final int  SUIJI_MODE=0;
//   //随机模式
//   public static final int  RANDOUM_PATTERN=1;
//   //固定模式
//   public static final int  FIXED_PATTERN=2;
   //随机模式
   public static final String  RANDOUM_PATTERN="1";
   //固定模式
   public static final String  FIXED_PATTERN="2";
   //app普通升级
   public static final int  UPGRADE_TYPE_PT=1;
   //app强制升级
   public static final int  UPGRADE_TYPE_QZ=2;
   
   //商品状态  100 待上架  200 已上架 300 已下架
   public static final int  PRODUCT_STATE_NOT_ONSELL=100;
   public static final int  PRODUCT_STATE_ONSELL=200;
   public static final int  PRODUCT_SOLT_OUT=300;
   
   //日期格式
   public static String FORMAT_DEFAULT_MIN = "yyyyMMddHHmmss";
   

}
