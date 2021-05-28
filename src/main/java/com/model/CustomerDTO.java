package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="customer")
@Table(name="customer")
public class CustomerDTO implements Cloneable, Serializable{
	@Id
	private String customerId;
	private String customerName;
	private String customerAddress;
	@Column(name="customerPhoneNum")
	private String customerPhone;
	@Column(name="customerAccountDetail")
	private String customerAccountDetails;
	@Column(name="customerGSTNum")
	private String customerGSTNumber;
	
	private static CustomerDTO customerDTO;
	
	synchronized public static CustomerDTO getCustomerDTO() {
		if(customerDTO==null) {
			customerDTO=new CustomerDTO();
		}
		return customerDTO.getCloneCustomerDTO();		
	}
	
	private CustomerDTO getCloneCustomerDTO() {
		try {
			return (CustomerDTO)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private CustomerDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAccountDetails() {
		return customerAccountDetails;
	}

	public void setCustomerAccountDetails(String customerAccountDetails) {
		this.customerAccountDetails = customerAccountDetails;
	}

	public String getcustomerGSTNumber() {
		return customerGSTNumber;
	}

	public void setcustomerGSTNumber(String customerGSTNumber) {
		this.customerGSTNumber = customerGSTNumber;
	}

	@Override
	public String toString() {
		return "CustomerDTO [customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + ", customerAccountDetails="
				+ customerAccountDetails + ", customerGSTNumber=" + customerGSTNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerGSTNumber == null) ? 0 : customerGSTNumber.hashCode());
		result = prime * result + ((customerAccountDetails == null) ? 0 : customerAccountDetails.hashCode());
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((customerPhone == null) ? 0 : customerPhone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		if (customerGSTNumber == null) {
			if (other.customerGSTNumber != null)
				return false;
		} else if (!customerGSTNumber.equals(other.customerGSTNumber))
			return false;
		if (customerAccountDetails == null) {
			if (other.customerAccountDetails != null)
				return false;
		} else if (!customerAccountDetails.equals(other.customerAccountDetails))
			return false;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerPhone == null) {
			if (other.customerPhone != null)
				return false;
		} else if (!customerPhone.equals(other.customerPhone))
			return false;
		return true;
	}
	
	
}