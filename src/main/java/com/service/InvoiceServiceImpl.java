package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.InvoiceTransRepository;
import com.model.InvoiceTransDTO;
import com.model.InvoiceTransEmbeddedId;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	InvoiceTransRepository invoiceTransRepo;

	@Override
	public void addInvoiecTrans(String invoiceId, String itemid, int qty) {
		InvoiceTransDTO invoiceTrans = InvoiceTransDTO.getInvoiceTransDTO();
		InvoiceTransEmbeddedId id = new InvoiceTransEmbeddedId();
		id.setInvoiceId(invoiceId);
		id.setItemId(itemid);
		
		invoiceTrans.setInvoiceTransId(id);
		invoiceTrans.setQty(qty);
		invoiceTransRepo.save(invoiceTrans);
	}
}
