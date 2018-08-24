package com.jfshare.mvp.server.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Created by lenovo on 2015/10/31.
 */
public class ConstantUtil {

	public static final String REDIS_IP_LIMIT = "order:limit:ip";

	public static final String REDIS_USER_LIMIT = "order:limit:userId";

	public static final String REDIS_ADDRESS_LIMIT = "order:limit:address";
	public final static String THIRD_PRODUCT = "jfx_third_product";
	public static final String REDIS_SZT_ORDER = "order:szt";

	public static final String REDIS_KEY_LianTone_OREDER = "LianTongOrder:list";

	public static final String ORDER_LIMIT_JSON = "{" + "\"list\": [" + "{" + "    \"type\": 1,"
			+ "   \"numLimit\": 10," + "   \"scoreLimit\": 100000" + "}," + "{" + " \"type\": 2," + " \"numLimit\": 10,"
			+ "\"scoreLimit\": 100000" + "}," + "{" + " \"type\": 3," + " \"numLimit\": 10," + "\"scoreLimit\": 100000"
			+ "}" + "]" + "}";

	// 支付状态前缀
	public static final String REDIS_KEY_PAY_STATE_PREFIX = "paystate:";
	public final static String REDIS_THIRD_ORDER = "thirdOrder:";
	// 订单操作日志
	public static final String REDIS_KEY_ORDER_OPT_QUEUE = "order_opt_queue";

	public static final String REDIS_KEY_VIRTUAL_OREDER = "vurtaulOrder:list";

	public static final String ES_ORDER_TYPE_NEW = "esOrder_new";
	public final static String APP_ID_NAME = "AppID";
	public final static String MER_ID_NAME = "MerID";
	public final static String SP_ID_NAME = "SPID";
	public final static String SIGN_NAME = "Sign";
	public final static String REQUEST_NO_NAME = "RequestNo";
	public final static String REQUEST_TIME_NAME = "RequestTime";
	public final static String MER_TYPE_NAME = "MerType";
	public final static String PAGE_SIZE_NAME = "PageSize";
	public final static String PAGE_INDEX_NAME = "PageIndex";
	public final static String GOODS_CODE_NAME = "GoodsCode";
	public final static String ORDER_ID_NAME = "OrderID";
	public final static String MERCHANT_ID_NAME = "MerchantID";

	// IsCount
	public final static String IS_COUNT_NAME = "IsCount";
	public final static String MER_TYPE_VALUE = "1";
	// 天翼获取商品列表的url
	public final static String TIANYI_PRODUCT_LIST_URL = "telefenAPI/merchant/GoodsList.do";
	// telefenAPI/merchant/GoodsDetail.do
	public final static String TIANYI_PRODUCT_DETAIL_URL = "telefenAPI/merchant/GoodsDetail.do";
	// 获取商品id列表的url
	public final static String TIANYI_PRODUCT_IDS_URL = "telefenAPI/merchant/News.do";

	// 预占库存订单
	public final static String TIANYI_ORDER_PAY_BEFORE_URL = "telefenAPI/DistOrder/Order.do";

	// 下单
	public final static String TIANYI_ORDER_PAY_AFTER_URL = "telefenAPI/DistOrder/Pay.do";

	// 查询分销订单详情
	public final static String TIANYI_ORDER_QUERY_URL = "telefenapi/DistOrder/Query.do";

	public final static String IS_COUNT_VALUE = "1";

	// 订单交易类型，Z0001积分兑换类，Z0002虚拟商品-话费直充，Z0003实体交易，Z0004货到付款，Z0005积分现金购，
	// Z0006天天红包，Z9001一元抢，Z9002限时抢，Z9003拍下立减，Z8001虚拟商品-卡密，Z8002虚拟商品-密码（兑换码）
	// Z8003话费充值 Z8004流量充值 Z8005Q币充值 Z8006游戏充值 Z8007 鹏淘充值卡券 Z8008 在线充值(北京深圳通地铁充值)
	// Z8009(地铁购票，也就是转账提现) Z8011(刮刮卡电子券充值)
	public static enum TRADE_CODE {//
		ORDER_CODE_EXCHANGE("Z0001"), ORDER_CODE_TEL_FARE("Z0002"), ORDER_CODE_ENTITY("Z0003"), ORDER_CODE_COD(
				"Z0004"), ORDER_CODE_SCORE_CASH("Z0005"), ORDER_CODE_DAY_HONGBAO(
						"Z0006"), ORDER_CODE_SUBSIDY_ONEYUANQIANG("Z9001"), ORDER_CODE_SUBSIDY_XIANSHIQIANG(
								"Z9002"), ORDER_CODE_SUBSIDY_LIJIAN("Z9003"), ORDER_CODE_VIR_KAMI(
										"Z8001"), ORDER_CODE_VIR_KAONLY("Z8002"), ORDER_CODE_RECHARGE(
												"Z8003"), ORDER_CODE_RECHARGE_FLOW("Z8004"), ORDER_CODE_RECHARGE_QB(
														"Z8005"), ORDER_CODE_RECHARGE_GAME(
																"Z8006"), ORDER_CODE_SZT_CARD(
																		"Z8007"), ORDER_GO_OUT_TICKET_ONLINE_RECHARGE(
																				"Z8008"), ORDER_GO_OUT_TICKET_ONLINE_BUYER(
																						"Z8009"), ORDER_GO_OUT_TICKET_ONLINE_SCRATCH_CARD(
																								"Z8011");
		private String enumVal;

		private TRADE_CODE(String enumVal) {
			this.enumVal = enumVal;
		}

		public String getEnumVal() {
			return this.enumVal;
		}

		public static boolean isVirOrder(String tradeCode) {
			boolean isVir = false;
			try {
				if (tradeCode.equals(ORDER_CODE_VIR_KAMI.enumVal)
						|| tradeCode.equals(ORDER_CODE_VIR_KAONLY.getEnumVal())
						|| tradeCode.equals(ORDER_GO_OUT_TICKET_ONLINE_SCRATCH_CARD.getEnumVal()))
					isVir = true;
			} catch (IllegalArgumentException e) {
				// Do nothing
			}
			return isVir;
		}

		// add at 20161109
		public static boolean isVirRechargeCheck(String tradeCode) {
			boolean isVir = false;
			try {
				if (!StringUtils.isEmpty(tradeCode)) {
					if (tradeCode.equals(ORDER_CODE_RECHARGE.enumVal)
							|| tradeCode.equals(ORDER_CODE_RECHARGE_FLOW.getEnumVal())
							|| tradeCode.equals(ORDER_CODE_RECHARGE_QB.getEnumVal()))
						isVir = true;
				}
			} catch (IllegalArgumentException e) {
				// Do nothing
			}
			return isVir;
		}
	};

	// 订单状态， 10:待支付 11:支付中 20:系统审核中 30:待发货 40:已发货|待收货 50:待评价 51:已评价 60:交易自动关闭
	// 62:交易买家取消 61:交易管理中心取消
	public static enum ORDER_STATE {
		WAIT_PAY(10), PAYING(11), WAIT_AUDIT(20), AUDIT_NO(21), WAIT_DELIVER(30), FINISH_DELIVER(40), WAIT_COMMENT(
				50), FINISH_COMMENT(51), TRADE_CLOSE(60), TRADE_CANCEL(62), TRADE_ADMIN_CANCEL(61);
		private int enumVal = 0;

		private ORDER_STATE(int enumVal) {
			this.enumVal = enumVal;
		}

		public int getEnumVal() {
			return this.enumVal;
		}

		public static boolean isOrderClosedCheck(int orderState) {
			return orderState == TRADE_CLOSE.getEnumVal() || orderState == TRADE_CANCEL.getEnumVal()
					|| orderState == TRADE_ADMIN_CANCEL.getEnumVal();
		}
	};

	// 支付状态，-1，失败，0初始，1成功，2进行中
	public static enum PAY_STATE {
		FAIL(-1), INIT(0), SUCCESS(1), ING(2);
		private int enumVal = 0;

		private PAY_STATE(int enumVal) {
			this.enumVal = enumVal;
		}

		public int getEnumVal() {
			return this.enumVal;
		}
	};

	// 售后状态1：新建（待审核） 2：审核通过 3：审核不通过 99：已完成
	public static enum AFTERSALE_STATE {
		WAIT_AUDIT(1, "新建（待审核)"), AUDIT_PASS(2, "审核通过"), AUDIT_UNPASS(3, "审核不通过"), FINISH(99, "已完成");
		private int enumVal = 0;
		private String desc = "";

		private AFTERSALE_STATE(int enumVal, String desc) {
			this.enumVal = enumVal;
			this.desc = desc;
		}

		public int getEnumVal() {
			return this.enumVal;
		}

		public String getDesc() {
			return desc;
		}

		public static AFTERSALE_STATE getEnumByVal(int v) {
			AFTERSALE_STATE e = null;
			switch (v) {
			case 1: {
				e = WAIT_AUDIT;
				break;
			}
			case 2: {
				e = AUDIT_PASS;
				break;
			}
			case 3: {
				e = AUDIT_UNPASS;
				break;
			}
			case 99: {
				e = FINISH;
			}
			}
			return e;
		}
	};

	public static enum ExportState {
		query, export, exception, expired
	}

	/**
	 * 交易类型 1:电积分兑换聚分享积分 2:聚分享积分兑换电信积分 3:线下消费抵扣 4:线上消费抵扣(支付扣减积分) 5:聚分享商城购物累积积分
	 * 6:聚分享商城活动赠送积分 7:支付后取消订单返还扣减积分 8:支付后取消订单扣减增送积分
	 *
	 */
	public static enum SCORE_TYPE {
		cost_offline(3), cost_online(4), order_rebate(5), rollback_cost_online(7), rollback_order_rebate(8);
		private int enumVal = 0;

		private SCORE_TYPE(int enumVal) {
			this.enumVal = enumVal;
		}

		public int getEnumVal() {
			return this.enumVal;
		}
	};

	public static enum THIRD_SELLER_ID {
		// 1表示我买网，2表示天翼e联盟
		WO_MAI_WANG_ID(63), E_LIAN_MENG_ID(115);
		private Integer enumVal;

		private THIRD_SELLER_ID(Integer enumVal) {
			this.enumVal = enumVal;
		}

		public Integer getEnumVal() {
			return this.enumVal;
		}
	};
}
