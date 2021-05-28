package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.InvoiceTransDTO;
import com.model.InvoiceTransEmbeddedId;

@Repository
public interface InvoiceTransRepository extends JpaRepository<InvoiceTransDTO, InvoiceTransEmbeddedId>{
	
}
