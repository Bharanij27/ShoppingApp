package com.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.InvoiceMasterDTO;

@Repository
@Transactional
public interface InvoiecMasterRepository extends JpaRepository<InvoiceMasterDTO, String>{
	public List<InvoiceMasterDTO> findByCustomerId(String customerId);
	public List<InvoiceMasterDTO> findByInvoiceDateBetween(String fromDate, String toDate);
	public InvoiceMasterDTO findByInvoiceId(String invoiceId);
	public List<InvoiceMasterDTO> findByInvoiceDateBetween(Date parse, Date parse2);
}
