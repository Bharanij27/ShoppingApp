package com.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.InvoiecMasterRepository;
import com.model.InvoiceMasterDTO;
import com.service.InvoiceMasterService;

@Service
@Transactional
public class InvoiceMasterServiceImpl implements InvoiceMasterService, Cloneable{

	@Autowired
	InvoiecMasterRepository invoiceMasterRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addNewInvoice(InvoiceMasterDTO newInvoice) {
		invoiceMasterRepo.save(newInvoice);
	}
	
	@Override
	public List<InvoiceMasterDTO> getByCustomer(String customerId) {
		return invoiceMasterRepo.findByCustomerId(customerId);
	}
	
	
//	static InvoiceMasterServiceImpl invoiceMasterService;
//	
//	public static InvoiceMasterServiceImpl getInvoiceMasterServiceImpl() {
//		if(invoiceMasterService == null) {
//			invoiceMasterService = new InvoiceMasterServiceImpl();
//		}
//		return invoiceMasterService.getClone();
//	}
//	
//	public InvoiceMasterServiceImpl getClone() {
//		try {
//			return (InvoiceMasterServiceImpl)super.clone();
//		}catch(Exception e) { 
//			e.printStackTrace();
//			return null;
//		}
//	}

	@Override
	public List<InvoiceMasterDTO> fetchByDate(Date fromDate, Date toDate) {
		return invoiceMasterRepo.findByInvoiceDateBetween(fromDate, toDate);
	}

	@Override
	public InvoiceMasterDTO fetchInvoiceByInvoiceId(String invoiceId) {
		return invoiceMasterRepo.findByInvoiceId(invoiceId);
	}

}
