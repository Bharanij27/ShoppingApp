package com.excelLab;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TableHeader {
	public void setHeader(XSSFWorkbook workbook, XSSFSheet wb, int rownum) {
		String [] obj = {"Name", "QTY", "Unit Price (Rs) ", "Total Price  (Rs) "};
		Row row = wb.createRow(rownum);
		int colnum = 0;
		for(String str : obj) {
			
			Cell cell = row.createCell(colnum);
			cell.setCellValue(str);

			CellRangeAddress cellrange = new CellRangeAddress(rownum, rownum, colnum, colnum + 1);
			wb.addMergedRegion(cellrange);
			styleHeader(workbook, cell);
			
			RegionUtil.setBorderLeft(BorderStyle.THIN, cellrange, wb);
			RegionUtil.setBorderRight(BorderStyle.THIN, cellrange, wb);
			RegionUtil.setBorderBottom(BorderStyle.THIN, cellrange, wb);
			RegionUtil.setBorderTop(BorderStyle.THIN, cellrange, wb);		
			
			colnum += 2;
		}
	}
	
	public void getHeader(XSSFSheet sheet) {
			Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) 
            {
                Cell cell = cellIterator.next();
                System.out.println("on table header " + cell.getStringCellValue());
            }
	}
	
	public void styleHeader(XSSFWorkbook workbook, Cell cell) {
		XSSFCellStyle style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED1.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    
	    XSSFFont font = workbook.createFont();
	    font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
	    font.setFontHeightInPoints((short)10);
	    font.setColor(IndexedColors.WHITE.getIndex());
	    style.setFont(font);
		cell.setCellStyle(style);
	}
}
