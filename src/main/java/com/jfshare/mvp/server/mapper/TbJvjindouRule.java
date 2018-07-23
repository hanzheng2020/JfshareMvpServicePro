package com.jfshare.mvp.server.mapper;

import java.util.Date;

public class TbJvjindouRule {
    private Integer id;

    private Date createTime;

    private String givingRule;

    private Integer randomGivingMin;

    private Integer randomGivingMax;

    private Integer fixedGiving;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGivingRule() {
        return givingRule;
    }

    public void setGivingRule(String givingRule) {
        this.givingRule = givingRule == null ? null : givingRule.trim();
    }

    public Integer getRandomGivingMin() {
        return randomGivingMin;
    }

    public void setRandomGivingMin(Integer randomGivingMin) {
        this.randomGivingMin = randomGivingMin;
    }

    public Integer getRandomGivingMax() {
        return randomGivingMax;
    }

    public void setRandomGivingMax(Integer randomGivingMax) {
        this.randomGivingMax = randomGivingMax;
    }

    public Integer getFixedGiving() {
        return fixedGiving;
    }

    public void setFixedGiving(Integer fixedGiving) {
        this.fixedGiving = fixedGiving;
    }

	@Override
	public String toString() {
		return "TbJvjindouRule [id=" + id + ", createTime=" + createTime
				+ ", givingRule=" + givingRule + ", randomGivingMin="
				+ randomGivingMin + ", randomGivingMax=" + randomGivingMax
				+ ", fixedGiving=" + fixedGiving + "]";
	}
}