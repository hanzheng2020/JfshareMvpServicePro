package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbLevelInfo{
    private Integer id;

    private Integer userid;

    private Date createTime;

    private Date updateTime;

    private Integer growthPoint;

    private String levle;

    private Integer jvjindou;

    private Integer levleBeyond;

    private Integer realJvjindou;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getGrowthPoint() {
        return growthPoint;
    }

    public void setGrowthPoint(Integer growthPoint) {
        this.growthPoint = growthPoint;
    }

    public String getLevle() {
        return levle;
    }

    public void setLevle(String levle) {
        this.levle = levle == null ? null : levle.trim();
    }

    public Integer getJvjindou() {
        return jvjindou;
    }

    public void setJvjindou(Integer jvjindou) {
        this.jvjindou = jvjindou;
    }

    public Integer getLevleBeyond() {
        return levleBeyond;
    }

    public void setLevleBeyond(Integer levleBeyond) {
        this.levleBeyond = levleBeyond;
    }

    public Integer getRealJvjindou() {
        return realJvjindou;
    }

    public void setRealJvjindou(Integer realJvjindou) {
        this.realJvjindou = realJvjindou;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "TbLevelInfo [id=" + id + ", userid=" + userid + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", growthPoint="
				+ growthPoint + ", levle=" + levle + ", jvjindou=" + jvjindou
				+ ", levleBeyond=" + levleBeyond + ", realJvjindou="
				+ realJvjindou + ", remark=" + remark + "]";
	}
}