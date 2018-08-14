package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbLevelInfo {
    private Integer id;

    private Integer userid;

    private Date createTime;

    private Date updateTime;

    private Integer growthPoint;

    private String grade;

    private Integer realJvjindou;

    private String remark;

    private Date queryTime;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
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

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

	@Override
	public String toString() {
		return "TbLevelInfo [id=" + id + ", userid=" + userid + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", growthPoint=" + growthPoint + ", grade=" + grade + ", realJvjindou=" + realJvjindou
				+ ", remark=" + remark + ", queryTime=" + queryTime + "]";
	}
    
    
}