package com.excelLab;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TableFooter {

	public void getFooter(XSSFSheet sheet) {
			Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) 
            {
                Cell cell = cellIterator.next();
                System.out.println(cell.getStringCellValue());
            }
	}

	public int setFooter(XSSFWorkbook wb, XSSFSheet sheet, int rownum, int lastColNum, double totalPrice, double discount, double tax, double extraCharge) {
		Map<String, String> footerValue = setFooterValue(totalPrice, discount, tax, extraCharge);
		
		for(Map.Entry<String, String> entry: footerValue.entrySet()) {
			int colNum = lastColNum;
			Row row = sheet.createRow(rownum++);
			Cell desc = row.createCell(colNum);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, colNum, colNum + 1));
			colNum +=2;
			desc.setCellValue(entry.getKey());
			
			Cell val = row.createCell(colNum);
			val.setCellValue(entry.getValue());
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, colNum, colNum + 1));
			styleBorderBottom(wb, val);
		}
		return rownum;
	}

	private Map<String, String> setFooterValue(double totalPrice, double discount, double tax, double extraCharge) {
		Map<String, String> footerValue = new LinkedHashMap<String, String>();
		DecimalFormat d = new DecimalFormat("#.##");
		
		footerValue.put("SubTotal", String.format("%.2f", totalPrice));
		footerValue.put("Discount", String.format("%.2f", discount));
		
		double subtotal =totalPrice + (totalPrice * (discount / 100));
		footerValue.put("After Discount", String.format("%.2f", subtotal));
		
		
		footerValue.put("Tax Rate", String.format("%.2f", tax));
		double totalAfterTax = subtotal + (subtotal * (tax / 100));
		footerValue.put("Tax Rate", String.format("%.2f", totalAfterTax));
		
		footerValue.put("Shipping", String.format("%.2f", extraCharge));
		
		double balance = subtotal + extraCharge;
		footerValue.put("Balance", String.format("%.2f", balance));

		return footerValue;
	}
	
	private void styleBorderBottom(XSSFWorkbook workbook, Cell cell) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cell.setCellStyle(style);
	}
	
}
