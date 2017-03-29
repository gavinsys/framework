package com.smartwork.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.jfinal.kit.PathKit;
import com.smartwork.common.model.Contacts;
import com.smartwork.common.model.Daily;
import com.smartwork.enums.EnumDepts;
import com.smartwork.enums.EnumJobs;

public class ToolExcel {
	private static final String BASE_PATH = PathKit.getWebRootPath()+File.separator+"WEB-INF"+File.separator+"files"+File.separator;
	public static void toExcel(List<Daily> list, String name) throws Exception {
		SXSSFWorkbook wb = new SXSSFWorkbook(-1);
		Map<String, CellStyle> styles = createStyles(wb);
		Sheet sheet = wb.createSheet();
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
        sheet.createFreezePane(0, 1);
        sheet.setColumnWidth(0, 256*12);
        sheet.setColumnWidth(1, 256*12);
        sheet.setColumnWidth(2, 256*12);
        sheet.setColumnWidth(3, 256*100);
        sheet.setColumnWidth(4, 256*20);
        sheet.setColumnWidth(5, 256*20);
        sheet.setZoom(75); 
        
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(20.75f);
		
		Cell cell_0 = headerRow.createCell(0);
		cell_0.setCellValue("部门");
		cell_0.setCellStyle(styles.get("header"));
		Cell cell_1 = headerRow.createCell(1);
		cell_1.setCellValue("职位");
		cell_1.setCellStyle(styles.get("header"));
		Cell cell_2 = headerRow.createCell(2);
		cell_2.setCellValue("所属");
		cell_2.setCellStyle(styles.get("header"));
		Cell cell_3 = headerRow.createCell(3);
		cell_3.setCellValue("标题");
		cell_3.setCellStyle(styles.get("header"));
		Cell cell_4 = headerRow.createCell(4);
		cell_4.setCellValue("开始时间");
		cell_4.setCellStyle(styles.get("header"));
		Cell cell_5 = headerRow.createCell(5);
		cell_5.setCellValue("结束时间");
		cell_5.setCellStyle(styles.get("header"));

		for (int rownum = 0; rownum < list.size(); rownum++) {
			Row row = sheet.createRow(rownum+1);

			Cell cell0 = row.createCell(0);
			cell0.setCellStyle(styles.get("cell_normal"));
			cell0.setCellValue(EnumDepts.findByCode(list.get(rownum).get("dept").toString()));

			Cell cell1 = row.createCell(1);
			cell1.setCellStyle(styles.get("cell_normal"));
			cell1.setCellValue(EnumJobs.findByCode(Integer.valueOf(list.get(rownum).get("jobs").toString())));

			Cell cell2 = row.createCell(2);
			cell2.setCellStyle(styles.get("cell_normal"));
			cell2.setCellValue(list.get(rownum).get("nameen").toString());

			Cell cell3 = row.createCell(3);
			cell3.setCellStyle(styles.get("cell_h"));
			cell3.setCellValue(list.get(rownum).getTitle());

			Cell cell4 = row.createCell(4);
			cell4.setCellStyle(styles.get("cell_normal_date"));
			cell4.setCellValue(list.get(rownum).getBegin());

			Cell cell5 = row.createCell(5);
			cell5.setCellStyle(styles.get("cell_normal_date"));
			if(list.get(rownum).getEnd()==null){
				cell5.setCellValue("");
			}else{
				cell5.setCellValue(list.get(rownum).getEnd());
			}
			

			if (rownum % 100 == 0) {
				((SXSSFSheet) sheet).flushRows(100);
			}

		}

		FileOutputStream out = new FileOutputStream(BASE_PATH+name+"_template.xlsx");
		wb.write(out);
		out.close();
		wb.dispose();
		wb.close();
	}
	
	public static void toExcel2(List<Contacts> list) throws Exception {
		SXSSFWorkbook wb = new SXSSFWorkbook(-1);
		Map<String, CellStyle> styles = createStyles(wb);
		Sheet sheet = wb.createSheet();
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
        sheet.createFreezePane(0, 1);
        sheet.setColumnWidth(0, 256*12);
        sheet.setColumnWidth(1, 256*12);
        sheet.setColumnWidth(2, 256*12);
        sheet.setColumnWidth(3, 256*100);
        sheet.setColumnWidth(4, 256*20);
        sheet.setColumnWidth(5, 256*20);
        sheet.setZoom(75); 
        
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(20.75f);
		
		Cell cell_0 = headerRow.createCell(0);
		cell_0.setCellValue("部门");
		cell_0.setCellStyle(styles.get("header"));
		Cell cell_1 = headerRow.createCell(1);
		cell_1.setCellValue("职位");
		cell_1.setCellStyle(styles.get("header"));
		Cell cell_2 = headerRow.createCell(2);
		cell_2.setCellValue("中文名");
		cell_2.setCellStyle(styles.get("header"));
		Cell cell_3 = headerRow.createCell(3);
		cell_3.setCellValue("英文名");
		cell_3.setCellStyle(styles.get("header"));
		Cell cell_4 = headerRow.createCell(4);
		cell_4.setCellValue("手机");
		cell_4.setCellStyle(styles.get("header"));
		Cell cell_5 = headerRow.createCell(5);
		cell_5.setCellValue("邮箱");
		cell_5.setCellStyle(styles.get("header"));

		for (int rownum = 0; rownum < list.size(); rownum++) {
			Row row = sheet.createRow(rownum+1);

			Cell cell0 = row.createCell(0);
			cell0.setCellStyle(styles.get("cell_normal"));
			cell0.setCellValue(EnumDepts.findByCode(list.get(rownum).get("dept").toString()));

			Cell cell1 = row.createCell(1);
			cell1.setCellStyle(styles.get("cell_normal"));
			cell1.setCellValue(EnumJobs.findByCode(Integer.valueOf(list.get(rownum).get("jobs").toString())));

			Cell cell2 = row.createCell(2);
			cell2.setCellStyle(styles.get("cell_normal"));
			cell2.setCellValue(list.get(rownum).getNamecn());

			Cell cell3 = row.createCell(3);
			cell3.setCellStyle(styles.get("cell_normal"));
			cell3.setCellValue(list.get(rownum).getNameen());

			Cell cell4 = row.createCell(4);
			cell4.setCellStyle(styles.get("cell_normal"));
			cell4.setCellValue(list.get(rownum).getPhone());

			Cell cell5 = row.createCell(5);
			cell5.setCellStyle(styles.get("cell_normal"));
			cell5.setCellValue(list.get(rownum).getEmail());
			

			if (rownum % 100 == 0) {
				((SXSSFSheet) sheet).flushRows(100);
			}

		}

		FileOutputStream out = new FileOutputStream(BASE_PATH+"_contacts.xlsx");
		wb.write(out);
		out.close();
		wb.dispose();
		wb.close();
	}

	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		DataFormat df = wb.createDataFormat();

		CellStyle style;
		Font headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("header", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		style.setDataFormat(df.getFormat("yyyy-MM-dd"));
		styles.put("header_date", style);

		Font font1 = wb.createFont();
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(font1);
		styles.put("cell_b", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font1);
		styles.put("cell_b_centered", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setFont(font1);
		style.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
		styles.put("cell_b_date", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setFont(font1);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(df.getFormat("d-mmm"));
		styles.put("cell_g", style);

		Font font2 = wb.createFont();
		font2.setColor(IndexedColors.BLUE.getIndex());
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(font2);
		styles.put("cell_bb", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setFont(font1);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(df.getFormat("d-mmm"));
		styles.put("cell_bg", style);

		Font font3 = wb.createFont();
		font3.setFontHeightInPoints((short) 14);
		font3.setColor(IndexedColors.DARK_BLUE.getIndex());
		font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(font3);
		style.setWrapText(true);
		styles.put("cell_h", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setWrapText(true);
		styles.put("cell_normal", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		styles.put("cell_normal_centered", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setWrapText(true);
		style.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
		styles.put("cell_normal_date", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setIndention((short) 1);
		style.setWrapText(true);
		styles.put("cell_indented", style);

		style = createBorderedStyle(wb);
		style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styles.put("cell_blue", style);

		return styles;
	}

	private static CellStyle createBorderedStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}
	
}
