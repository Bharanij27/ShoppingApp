package com.control;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.component.InvoiceComponent;
import com.service.InvoiceMasterServiceImpl;
import com.model.InvoiceMasterDTO;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	InvoiceComponent invoiceComponent;

	@Autowired
	InvoiceMasterServiceImpl invoiceMasterService;
	
	public InvoiceComponent getInvoiceComponent() {
		return invoiceComponent;
	}

	public void setInvoiceComponent(InvoiceComponent invoiceComponent) {
		this.invoiceComponent = invoiceComponent;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String storeInvoice(HttpServletRequest req, HttpSession session) {
		String generatedCustomerId = (String) session.getAttribute("customerId");
		String customerId = req.getParameter("customerId");
		if(customerId == null) {
			customerId = generatedCustomerId;
			invoiceComponent.createCustomer(req, generatedCustomerId);
		}
		String invoiceId = (String) session.getAttribute("invoiceId");
		invoiceComponent.storeInvoiceItems(session, invoiceId);
		invoiceComponent.storeInvoiceMaster(session, invoiceId, customerId);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/ByCustomer", method = RequestMethod.POST)
	public String createExcelFromInvoice(HttpServletRequest req, HttpSession session) {
		try {
			String customerId = req.getParameter("customerId");
			List<InvoiceMasterDTO> invoices = invoiceMasterService.getByCustomer(customerId);			
			
			if(invoices.size() <= 0) {
				System.out.println("Not a valid customer");
				return "redirect:/home";
			}
			
			storeExcelLocally(invoices, session);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error";
		}
		return "redirect:/getEmail";
	}
	
	@RequestMapping(value="/byDate", method = RequestMethod.POST)
	public String createExcelByDate(HttpServletRequest req, HttpSession session) {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fromDate").toString());  
		    Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("toDate").toString());  
		    
		    
		    System.out.println(fromDate);
			if(fromDate.compareTo(toDate) > 0) {
				Date temp = fromDate;
				fromDate = toDate;
				toDate = temp;
			}
						
			List<InvoiceMasterDTO> invoices = invoiceMasterService.fetchByDate(fromDate, toDate);
			
			if(invoices.size() <= 0) {
				System.out.println("Not a valid customer");
				return "redirect:/home";
			}
			storeExcelLocally(invoices, session);	
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error";
		}
		return "redirect:/getEmail";
	}
	
	@RequestMapping(value = "/ByInvoiceId", method = RequestMethod.POST)
	public String createExcelByCustomer(HttpServletRequest req, HttpSession session) {
		try {
			String invoiceId = req.getParameter("invoiceId").toString();
			InvoiceMasterDTO invoice = invoiceMasterService.fetchInvoiceByInvoiceId(invoiceId);
			
			if(invoice == null || invoice.getCustomerId() == null) {
				System.out.println("Not a valid Invoice Id");
				return "redirect:/home";
			}
			
			List<InvoiceMasterDTO> invoices = new ArrayList<InvoiceMasterDTO>();
			invoices.add(invoice);		
			storeExcelLocally(invoices, session);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error";
		}
		return "redirect:/getEmail";
	}
	
	public void storeExcelLocally(List<InvoiceMasterDTO> invoices, HttpSession session) throws Exception{
		int c = 0;
		ArrayList<String> filePath = new ArrayList<String>();

		for (Iterator<InvoiceMasterDTO> iterator = invoices.iterator(); iterator.hasNext();) {
			InvoiceMasterDTO in = (InvoiceMasterDTO) iterator.next();
			InputStream inputStream = new ByteArrayInputStream(in.getInvoice());
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			FileOutputStream out = new FileOutputStream(new File("/Users/bharani/git/Day21Ex" + in.getInvoiceId() +".xlsx"));
			System.out.println("/Users/bharani/git/Day21Ex" + in.getInvoiceId() +".xlsx");
			
	       	
			filePath.add(c++, "/Users/bharani/git/Day21Ex" + in.getInvoiceId() + ".xlsx");
	       	filePath.add(c++, "/Users/bharani/git/Day21Ex" + in.getInvoiceId() + ".pdf");		       	
			
			workbook.write(out);
			out.close();			
			
			session.setAttribute("InvoicePath", filePath);
			session.setAttribute("smsData", invoices);
			convertToPDF("/Users/bharani/git/Day21Ex" + in.getInvoiceId());
		}
		
	}
	
	public static void convertToPDF(String path) {
		try {
			Workbook workbook = new Workbook(path + ".xlsx");
			workbook.save(path + ".pdf", SaveFormat.PDF); 	// Save the document in PDF format
			System.out.println("Converted to pdf suceessfully.....");		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InvoiceMasterServiceImpl getInvoiceMasterService() {
		return invoiceMasterService;
	}

	public void setInvoiceMasterService(InvoiceMasterServiceImpl invoieMasterService) {
		this.invoiceMasterService = invoieMasterService;
	}
}
