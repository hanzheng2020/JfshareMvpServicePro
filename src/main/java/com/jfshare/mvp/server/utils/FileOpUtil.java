package com.jfshare.mvp.server.utils;

import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.model.TbProductSurvey;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Created by hanzheng on 18/7/26.
 */
@Component
public class FileOpUtil {
	public static byte[] getExportProduct(List<TbProductSurvey> list) {

		String exportTitle = "商品列表";
		String[] colNames = new String[] { "商品ID", "商品名称", "商品价格", "类目", "状态", "赠送聚金豆", "创建时间"};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, colNames.length - 1);
		sheet.addMergedRegion(cra);

		try {
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeight((short) 2000);
			row.setHeightInPoints(100);
			HSSFCellStyle style0 = wb.createCellStyle();
			style0.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style0.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style0.setWrapText(true);
			Font ztFont0 = wb.createFont();
			ztFont0.setFontName("宋体");
			ztFont0.setFontHeightInPoints((short) 11);
			ztFont0.setColor(HSSFColor.GREY_50_PERCENT.index);
			style0.setFont(ztFont0);

			HSSFCell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
			cell.setCellValue(exportTitle);
			cell.setCellStyle(style0);

			// 第1行 导出列标题
			row = sheet.createRow(1);
			row.setHeight((short) 242);
			row.setHeightInPoints(12.1f);
			HSSFCellStyle style1 = wb.createCellStyle();
			Font ztFont1 = wb.createFont();
			ztFont1.setFontName("Arial");
			ztFont1.setFontHeightInPoints((short) 11);
			style1.setFont(ztFont1);
			for (int i = 0; i < colNames.length; i++) {
				cell = row.createCell(i, Cell.CELL_TYPE_STRING);
				cell.setCellValue(colNames[i]);
				cell.setCellStyle(style1);
			}

			// 第2开始的数据行
			int curRowIndex = 2;
			HSSFCellStyle style2 = wb.createCellStyle();
			Font ztFont2 = wb.createFont();
			ztFont2.setFontName("Arial");
			style2.setFont(ztFont2);
			boolean isInit = false;
			// 用来处理脏数据
			Map<String, Boolean> hashMap = new HashMap<String, Boolean>();

			for (TbProductSurvey product : list) {
				// "商品ID", "商品名称", "商品价格", "类目", "状态", "赠送聚金豆", "创建时间"
						row = sheet.createRow(curRowIndex++);
						row.setRowStyle(style2);
						HSSFCell cell0 = row.createCell(0, Cell.CELL_TYPE_STRING);
						cell0.setCellValue(product.getProductId());

						HSSFCell cell1 = row.createCell(1, Cell.CELL_TYPE_STRING);
						cell1.setCellValue(product.getProductName());

						HSSFCell cell2 = row.createCell(2, Cell.CELL_TYPE_STRING);
						cell2.setCellValue(product.getCurPrice());

						HSSFCell cell3 = row.createCell(3, Cell.CELL_TYPE_STRING);
						cell3.setCellValue(product.getSubjectName());

						HSSFCell cell4 = row.createCell(4, Cell.CELL_TYPE_STRING);
						if ((Constant.PRODUCT_STATE_NOT_ONSELL + "").equals(product.getActiveState())) {
							cell4.setCellValue("待上架");
						}else if((Constant.PRODUCT_STATE_ONSELL + "").equals(product.getActiveState())) {
							cell4.setCellValue("已上架");
						}else {
							cell4.setCellValue("已下架");
						}

						HSSFCell cell5 = row.createCell(5, Cell.CELL_TYPE_STRING);
						cell5.setCellValue(product.getPresentExp() + "");

						HSSFCell cell6 = row.createCell(6, Cell.CELL_TYPE_STRING);
						cell6.setCellValue(DateUtils.dateToStr(product.getCreateTime(),"yyyy-MM-dd"));

			}
		} catch (Exception e) {
			return null;
		}
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				wb.write(bos);
			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bytes;
	}
	
	/**
	 * 上传文件
	 *
	 * @param fileBuff
	 * @param uploadFileName
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(byte[] fileBuff, String uploadFileName) throws Exception {
		URL url4Oss =null;
		try {
			if (uploadFileName.contains(".") == false) {
				return null;
			}

			String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);


			InputStream input = new ByteArrayInputStream(fileBuff);
			String key = OSSUtils.uploadFile2OssForTemp(input, uploadFileName);
			url4Oss = OSSUtils.getUrlFromTempDir(uploadFileName);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return url4Oss.toString();
	}
}

