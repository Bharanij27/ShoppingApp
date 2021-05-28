package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.CustomerRepository;
import com.model.CustomerDTO;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public void createNewCustomer(CustomerDTO customer) {
		customerRepo.save(customer);
	}
	
	@Override
	public CustomerDTO findCustomerById(String cid) {
		return customerRepo.findBycustomerId(cid);
	}
}
