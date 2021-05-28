package com.excelLab;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.CustomerDTO;

public interface PdfConverter extends Remote{ 
	public void autoFit(XSSFWorkbook workbook) throws RemoteException;
	public byte[] createExcel(CustomerDTO customer, Map<String, Object> tableInfo, String invoiceId, Date now) throws Exception;
}
