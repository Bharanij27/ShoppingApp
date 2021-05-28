package com.excelLab;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.CustomerDTO;

public class ExcelServer extends UnicastRemoteObject implements PdfConverter, Serializable, Cloneable{
	
private static ExcelServer excelServer;
	
	synchronized public static ExcelServer getExcelServer() throws Exception {
		if(excelServer == null) {
			excelServer = new ExcelServer();
		}
		return excelServer.getCloneExcelServer();		
	}
	
	private ExcelServer getCloneExcelServer() {
		try {
			return (ExcelServer)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	NewWorkBook wb;
	public ExcelServer() throws RemoteException{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public byte[] createExcel(CustomerDTO customer, Map<String, Object> data, String invoiceId, Date date) {
		try {
		wb  = new NewWorkBook();
		XSSFWorkbook workbook = wb.getWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Invoice");
	     
	    // create blank 
	    int rownum = createColoredRow(workbook, sheet, 0);
	    
	    rownum = blankRow(sheet, rownum, 2);
	    
	    rownum = addInvoiceData(workbook, sheet, rownum, invoiceId, date);

	    rownum = blankRow(sheet, rownum, 2);

	    rownum = addCustomer(workbook, sheet, customer, rownum);

	    rownum = blankRow(sheet, rownum, 2);
	    
	    rownum = addUserData(workbook, sheet, rownum);

	    rownum = blankRow(sheet, rownum, 2);

	    TableHeader header = new TableHeader();
	    header.setHeader(workbook ,sheet, rownum);
	    rownum++;

	    double totalPrice = 0;
	    int lastColNum = 0;
	        //Iterate over data and write to sheet
	    Set<String> keyset = data.keySet();
	    
	    for (String key : keyset)
	    {
	    	Row row = sheet.createRow(rownum++);
	    	Object [] objArr = (Object[]) data.get(key);
	        int cellnum = 0;
		    for (int i = 0; i < objArr.length; i++)
		    {
		    	Object obj = objArr[i];
		        Cell cell = row.createCell(cellnum);
		        cell.setCellValue(obj.toString());
		        
		        CellRangeAddress cellrange = new CellRangeAddress(rownum, rownum, cellnum, cellnum + 1);
		        System.out.println(rownum +" " + rownum +" " + cellnum  +" " + cellnum + 1);
		        sheet.addMergedRegion(cellrange);
				RegionUtil.setBorderLeft(BorderStyle.THIN, cellrange, sheet);
				RegionUtil.setBorderRight(BorderStyle.THIN, cellrange, sheet);
				RegionUtil.setBorderBottom(BorderStyle.THIN, cellrange, sheet);
				RegionUtil.setBorderTop(BorderStyle.THIN, cellrange, sheet);	
		        
		        lastColNum = cellnum;
				cellnum +=2;		        
		        
		        if(i == objArr.length - 1) {
		        	Cell totalCol = row.createCell(cellnum);
		        	
		        	int qty = (int)objArr[1];
		        	float price = (float)objArr[i];
		        	float total = qty * price;
		        	
		        	totalCol.setCellValue(Float.toString(total));
		        	totalPrice += total;
		        	
		        	cellrange = new CellRangeAddress(rownum, rownum, cellnum, cellnum + 1);
		        	sheet.addMergedRegion(cellrange);
					RegionUtil.setBorderLeft(BorderStyle.THIN, cellrange, sheet);
					RegionUtil.setBorderRight(BorderStyle.THIN, cellrange, sheet);
					RegionUtil.setBorderBottom(BorderStyle.THIN, cellrange, sheet);
					RegionUtil.setBorderTop(BorderStyle.THIN, cellrange, sheet);	
		       	}
		        
		     }
		 }

	     TableFooter tf = new TableFooter();
	     rownum = tf.setFooter(workbook, sheet, rownum, lastColNum, totalPrice, 10, 5, 10);

		 rownum = blankRow(sheet, rownum, 2);

		createColoredRow(workbook, sheet, rownum);
		 
		//autoFit(workbook);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workbook.write(bos);
		System.out.println("created on server");
		return bos.toByteArray();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return null;		
	}
		
	private int addCustomer(XSSFWorkbook workbook, XSSFSheet sheet, CustomerDTO customer, int rownum) {
		Row header = sheet.createRow(rownum);
		Cell headerValue = header.createCell(0);
		CellRangeAddress cellrange =new CellRangeAddress(rownum, rownum, 0, 7);
		sheet.addMergedRegion(cellrange);
		headerValue.setCellValue("Bill To");
		styleBorderBottom(workbook, headerValue);
		RegionUtil.setBorderBottom(BorderStyle.THIN, cellrange, sheet);
		rownum++;
		
		
		Row cname = sheet.createRow(rownum);
		Cell name = cname.createCell(0);
		name.setCellValue(customer.getCustomerName());
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 2));
		rownum++;
		
		Row caddress = sheet.createRow(rownum);
		Cell address = caddress.createCell(0);
		address.setCellValue(customer.getCustomerAddress());
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 5));
		rownum++;
		
		Row cphnum = sheet.createRow(rownum);
		Cell phnum = cphnum.createCell(0);
		phnum.setCellValue(customer.getCustomerPhone());
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 2));
		rownum++;

		return rownum;
	}
	
	private int addUserData(XSSFWorkbook workbook, XSSFSheet sheet, int rownum) {
		Row header = sheet.createRow(rownum);
		Cell headerValue = header.createCell(0);
		CellRangeAddress cellrange =new CellRangeAddress(rownum, rownum, 0, 7);
		headerValue.setCellValue("Bill From");
		styleBorderBottom(workbook, headerValue);
		sheet.addMergedRegion(cellrange);
		RegionUtil.setBorderBottom(BorderStyle.THIN, cellrange, sheet);
		rownum++;
		
		Row cname = sheet.createRow(rownum);
		Cell name = cname.createCell(0);
		name.setCellValue("ABC Shop");
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 2));
		rownum++;
		
		Row caddress = sheet.createRow(rownum);
		Cell address = caddress.createCell(0);
		address.setCellValue("4 Mannavan Road, ch-21");
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 5));
		rownum++;
		
		Row cphnum = sheet.createRow(rownum);
		Cell phnum = cphnum.createCell(0);
		phnum.setCellValue("9875876540");
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 2));
		rownum++;
		
		return rownum;
	}

	private int addInvoiceData(XSSFWorkbook workbook, XSSFSheet sheet, int rownum, String invoiceId, Date date) {
		Row idRow = sheet.createRow(rownum);
	
		Cell idLabel = idRow.createCell(0);
		idLabel.setCellValue("Invoice Id");
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 1));
		
		Cell id = idRow.createCell(2);
		id.setCellValue(invoiceId);
		styleBorderBottom(workbook, id);
		CellRangeAddress cellrange = new CellRangeAddress(rownum, rownum, 2, 3);
		sheet.addMergedRegion(cellrange);
		RegionUtil.setBorderBottom(BorderStyle.THIN, cellrange, sheet);
		
		rownum++;		
		Row dateRow = sheet.createRow(rownum);		
			
		Cell dateLabel = dateRow.createCell(0);
		dateLabel.setCellValue("Invoice Date");
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 1));

		Cell dateValue = dateRow.createCell(2);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		dateValue.setCellValue(formatter.format(date));
		styleBorderBottom(workbook, dateValue);
		cellrange = new CellRangeAddress(rownum, rownum, 2, 3);
		sheet.addMergedRegion(cellrange);
		RegionUtil.setBorderBottom(BorderStyle.THIN, cellrange, sheet);
		
		rownum++;
		
		return rownum;
	}
	
	private void styleBorderBottom(XSSFWorkbook workbook, Cell cell) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cell.setCellStyle(style);
	}	
	
	private void styleBorder(XSSFWorkbook workbook, Cell cell) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cell.setCellStyle(style);
	}
	
	private int blankRow(XSSFSheet sheet, int rownum, int rowCount) {
		for(int i = 0; i < rowCount; i++) {			
			Row idRow = sheet.createRow(rownum++);
		}
		return rownum;
	}

	private int createColoredRow(XSSFWorkbook workbook, XSSFSheet sheet, int rowId) {
		CellStyle cellStyle = workbook.createCellStyle();
        Row row = sheet.createRow(rowId++);
        Cell cell = row.createCell(0);

        cellStyle.setFillForegroundColor(IndexedColors.RED1.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(CellRangeAddress.valueOf(String.format("A%d:I%d", rowId , rowId )));

        return rowId;
	}

	@Override
	public void autoFit(XSSFWorkbook workbook) throws RemoteException {
		XSSFSheet sheet = workbook.getSheetAt(0);
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println(colCount);
		for(int i = 0; i < colCount; i++) {
			sheet.autoSizeColumn(i);
		}    
    }
	
	public static void main(String[] args) throws Exception {
		ExcelServer excel = new ExcelServer();
		LocateRegistry.createRegistry(4070);		
		
		System.out.println("Server Ready....");
		Naming.bind("rmi://localhost:4070/rmiservice/PdfConverter", excel);
	}
	
}

class NewWorkBook implements Serializable{
	public XSSFWorkbook workbook;
	
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public NewWorkBook() {
		XSSFWorkbook wb  = new XSSFWorkbook(); 
		this.workbook = wb;
	}	
}
