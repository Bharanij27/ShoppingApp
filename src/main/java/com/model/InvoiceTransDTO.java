package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="invoiceTrans")
@Table(name="invoiceTrans")
public class InvoiceTransDTO implements Serializable, Cloneable{
	
	@EmbeddedId
	private InvoiceTransEmbeddedId invoiceTransId;
	
	@Column(name="quantity")
	private int qty;
	

	private static InvoiceTransDTO invoiceTransDTO;
	synchronized public static InvoiceTransDTO getInvoiceTransDTO() {
		if(invoiceTransDTO == null) {
			invoiceTransDTO = new InvoiceTransDTO();
		}
		return invoiceTransDTO.getCloneItemDTO();		
	}
	

	public static void setInvoiceTransDTO(InvoiceTransDTO invoiceTransDTO) {
		InvoiceTransDTO.invoiceTransDTO = invoiceTransDTO;
	}

	private InvoiceTransDTO getCloneItemDTO() {
		try {
			return (InvoiceTransDTO)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public InvoiceTransEmbeddedId getInvoiceTransId() {
		return invoiceTransId;
	}
	
	public void setInvoiceTransId(InvoiceTransEmbeddedId invoiceTransId) {
		this.invoiceTransId = invoiceTransId;
	}

	private InvoiceTransDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invoiceTransId == null) ? 0 : invoiceTransId.hashCode());
		result = prime * result + qty;
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
		InvoiceTransDTO other = (InvoiceTransDTO) obj;
		if (invoiceTransId == null) {
			if (other.invoiceTransId != null)
				return false;
		} else if (!invoiceTransId.equals(other.invoiceTransId))
			return false;
		if (qty != other.qty)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "InvoiceTransDTO [invoiceTransId=" + invoiceTransId + ", qty=" + qty + "]";
	}
}
