package com.jfshare.bonusPoints.bonuspoints.monitor;

import com.google.common.collect.Lists;

import java.util.List;

public enum MetricNames implements IMetricName {
    Address_Add("地址添加"),
	
    USER_LOGIN("用户登录"),
    USER_CHANGE_PWD("用户修改密码"),
    ORDER_BOOKING("下单"),
    ORDER_LIST("订单列表"),
    ORDER_REFUND("退款");

    private String desc;

    MetricNames(String desc) {
        this.desc = desc;
    }


    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public static List<IMetricName> getAllMetrics() {
        return Lists.newArrayList(MetricNames.values());
    }
}
