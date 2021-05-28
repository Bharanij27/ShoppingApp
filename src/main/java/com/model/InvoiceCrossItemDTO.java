package com.model;

public class InvoiceCrossItemDTO {
	private String itemName;
	private int quantity;
	private float itemPrice;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + Float.floatToIntBits(itemPrice);
		result = prime * result + quantity;
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
		InvoiceCrossItemDTO other = (InvoiceCrossItemDTO) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (Float.floatToIntBits(itemPrice) != Float.floatToIntBits(other.itemPrice))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InvoiceCrossItemDTO [itemName=" + itemName + ", quantity=" + quantity + ", itemPrice=" + itemPrice
				+ "]";
	}
	
}
