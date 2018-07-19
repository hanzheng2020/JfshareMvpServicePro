package com.jfshare.mvp.server.utils;

import java.io.Serializable;
import java.util.List;

public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4444017239100620999L;

    // 表头
    private List<Object> titles;

    // 数据
    private List<List<Object>> rowDatas;

    // 页签名称
    private String sheetName = "sheet1";

    public List<Object> getTitles() {
        return titles;
    }

    public void setTitles(List<Object> titles) {
        this.titles = titles;
    }

    public List<List<Object>> getRowDatas() {
        return rowDatas;
    }

    public void setRowDatas(List<List<Object>> rowDatas) {
        this.rowDatas = rowDatas;
    }

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}

