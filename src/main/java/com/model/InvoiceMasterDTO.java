package com.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="invoiceMaster")
@Table(name="invoiceMaster")
public class InvoiceMasterDTO implements Serializable, Cloneable{
	@Id
	private String invoiceId;
	private Date invoiceDate; 
	private String customerId;
	private byte[] invoice;
	private static InvoiceMasterDTO invoiceMasterDTO;
	

	synchronized public static InvoiceMasterDTO getInvoiceMasterDTO() {
		if(invoiceMasterDTO == null) {
			invoiceMasterDTO = new InvoiceMasterDTO();
		}
		return invoiceMasterDTO.getCloneInvoiceMasterDTO();		
	}
	
	private InvoiceMasterDTO getCloneInvoiceMasterDTO() {
		try {
			return (InvoiceMasterDTO)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}	

	public InvoiceMasterDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date now) {
		this.invoiceDate = now;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public byte[] getInvoice() {
		return invoice;
	}

	public void setInvoice(byte[] invoice) {
		this.invoice = invoice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + Arrays.hashCode(invoice);
		result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
		result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
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
		InvoiceMasterDTO other = (InvoiceMasterDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (!Arrays.equals(invoice, other.invoice))
			return false;
		if (invoiceDate == null) {
			if (other.invoiceDate != null)
				return false;
		} else if (!invoiceDate.equals(other.invoiceDate))
			return false;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvoiceMasterDTO [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", customerId="
				+ customerId + ", invoice=" + Arrays.toString(invoice) + "]";
	}
}
