package com.jfshare.mvp.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Excel 工具类
 * @author fengxiang
 * @date 2018-07-09
 */
public class ExcelUtils {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static HSSFWorkbook createExcel(List<ExcelData> excelDatas) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		for (ExcelData excelData : excelDatas) {
			HSSFSheet sheet = workbook.createSheet(excelData.getSheetName());
			sheet = createRow(sheet, excelData.getTitles(), 0);
			for (int i = 0; i < excelData.getRowDatas().size(); i ++) {
				sheet = createRow(sheet, excelData.getRowDatas().get(i), i + 1);
			}
			for (int c = 0; c < excelData.getTitles().size(); c ++) {
				sheet.autoSizeColumn(c);
			}
		}
		return workbook;
	}
	
	private static HSSFSheet createRow(HSSFSheet sheet, List<Object> rowData, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		for (int i = 0; i < rowData.size(); i ++) {
			Object cellData = rowData.get(i);
			if (cellData == null) {
				continue;
			}
			if (cellData instanceof Date) {
				row.createCell(i).setCellValue(format.format((Date) cellData));
			} else {
				row.createCell(i).setCellValue(cellData.toString());
			}
		}
		return sheet;
	}
	
}
