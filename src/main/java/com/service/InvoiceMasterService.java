package com.service;

import java.util.Date;
import java.util.List;

import com.model.InvoiceMasterDTO;

public interface InvoiceMasterService {

	InvoiceMasterDTO fetchInvoiceByInvoiceId(String invoiceId);
	List<InvoiceMasterDTO> getByCustomer(String customerId);
	void addNewInvoice(InvoiceMasterDTO newInvoice);
//	List<InvoiceMasterDTO> fetchByDate(String fromDate, String toDate);
	List<InvoiceMasterDTO> fetchByDate(Date fromDate, Date toDate);

}
