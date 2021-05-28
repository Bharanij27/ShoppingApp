package com.component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excelLab.ExcelServer;
import com.model.CustomerDTO;
import com.model.InvoiceMasterDTO;
import com.service.CustomerService;
import com.service.InvoiceMasterService;
import com.service.InvoiceMasterServiceImpl;
import com.service.InvoiceService;
import com.service.ItemService;
import com.service.ItemServiceImpl;

@Component
@Transactional
public class InvoiceComponent {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	ItemService itemServiceImpl;
	
	@Autowired
	InvoiceMasterService invoiceMasterServiceImpl;
	
	public void createCustomer(HttpServletRequest req,String customerId) {
		CustomerDTO customer = CustomerDTO.getCustomerDTO();
		customer.setCustomerId(customerId);
		customer.setCustomerName(req.getParameter("cname"));
		customer.setCustomerAddress(req.getParameter("address"));
		customer.setCustomerPhone(req.getParameter("phnum"));
		customer.setCustomerAccountDetails(req.getParameter("accnum"));
		customer.setcustomerGSTNumber(req.getParameter("gstnum"));
		customerService.createNewCustomer(customer);
	}
	
	public CustomerDTO fetchCustomer(String customerId) {
		return customerService.findCustomerById(customerId);
	}

	public void storeInvoiceItems(HttpSession session, String invoiceId) {
		Enumeration<String> e = session.getAttributeNames();
		
		while(e.hasMoreElements() && invoiceId != null){
			String name = e.nextElement();
			String value = (String)session.getAttribute(name);
			if(!name.equals("uname") && !name.equals("invoiceId") && !name.equals("nextPage") && !name.equals("customerId")){
				System.out.println(name + "->"+ value);
				invoiceService.addInvoiecTrans(invoiceId, name, Integer.parseInt(value));
			}
		}
	}

	public void storeInvoiceMaster(HttpSession session, String invoiceId, String customerId) {
		Date currentDate = new Date();	
		System.out.println(currentDate);
		InvoiceMasterDTO newInvoice = InvoiceMasterDTO.getInvoiceMasterDTO();
		newInvoice.setCustomerId(customerId);
		newInvoice.setInvoiceId(invoiceId);
		newInvoice.setInvoiceDate(currentDate);
		newInvoice.setInvoice(createExcel(invoiceId, currentDate, fetchCustomer(customerId)));
		
		invoiceMasterServiceImpl.addNewInvoice(newInvoice);	
	}
	
	private byte[] createExcel(String invoiceId, Date now, CustomerDTO customer){
		byte[] bs = null;
		try {
			Map<String, Object> invoice = fetchInvoiceItem(invoiceId);	// get item info in invoice
			ExcelServer server = ExcelServer.getExcelServer();
			bs = server.createExcel(customer, invoice, invoiceId, now);
//			InputStream inputStream = new ByteArrayInputStream(bs);
//	       	XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//	       	FileOutputStream out = new FileOutputStream(new File("/Users/bharani/git/Day21Ex" + invoiceId +".xlsx"));
//	       	workbook.write(out);
//			out.close();
//			System.out.println("/Users/bharani/git/Day21Ex" + invoiceId + ".xlsx");
			return bs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bs;
	}
		
	private Map<String, Object> fetchInvoiceItem(String invoiceId){
		Map<String, Object> item = itemServiceImpl.fetchByInvoiceId(invoiceId);
		return item;
	}
}
