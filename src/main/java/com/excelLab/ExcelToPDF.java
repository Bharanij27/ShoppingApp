package com.excelLab;

import java.io.Serializable;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;


public class ExcelToPDF implements Serializable{
	public static void main(String[] args) throws Exception 
    {   
        try
	    {
//        	PdfConverter excelConverter = (PdfConverter)Naming.lookup("rmi://localhost:4070/rmiservice/PdfConverter");
//        	byte[] bs = excelConverter.createExcel();
//        	InputStream inputStream = new ByteArrayInputStream(bs);
//
////        	byte[] source = excelConverter.createExcel();
////        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
////        	// write bytes to bos ...
////        	byte[] sink = bos.toByteArray();        	
////        	ByteArrayInputStream bis = new ByteArrayInputStream(source);
////        	InputStream stream = new ByteArrayOutputStream(bs);
//
// 
//	        //Write the workbook in file system
//        	XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//	        FileOutputStream out = new FileOutputStream(new File("invoice.xlsx"));
//	        workbook.write(out);
//	        out.close();
	        System.out.println("invoice.xlsx written successfully on disk.");
	        convertToPDF();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
    }
	
	public static void convertToPDF() throws Exception {
		Workbook workbook = new Workbook("invoice.xlsx");
		workbook.save("Excel-to-PDF.pdf", SaveFormat.PDF); 	// Save the document in PDF format
		System.out.println("Converted to pdf suceessfully.....");
	}
}