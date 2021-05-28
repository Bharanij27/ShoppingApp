package com.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.CustomerDTO;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerDTO, String>{
	public CustomerDTO findBycustomerId(String customerId);
	public List<CustomerDTO> findAll();
//	public CustomerDTO fetchDataFromInvoice(String invoiceId);
}
