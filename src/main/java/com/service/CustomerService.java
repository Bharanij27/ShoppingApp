package com.service;

import com.model.CustomerDTO;

public interface CustomerService {

	public void createNewCustomer(CustomerDTO customer);
	public CustomerDTO findCustomerById(String cid);

}
