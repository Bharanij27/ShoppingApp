package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="item")
@Table(name="item")
public class ItemDTO implements Serializable,Cloneable{
	@Id
	private String itemId;
	private String itemName;
	@Column(name="itemPrice")
	private float price;
	private String imageLoc;
	private String shopId;


	private static ItemDTO itemDTO;
	synchronized public static ItemDTO getItemDTO() {
		if(itemDTO==null) {
			itemDTO=new ItemDTO();
		}
		return itemDTO.getCloneItemDTO();		
	}
	
	private ItemDTO getCloneItemDTO() {
		try {
			return (ItemDTO)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ItemDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getItemid() {
		return itemId;
	}

	public void setItemid(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImageLoc() {
		return imageLoc;
	}

	public void setImageLoc(String imageLoc) {
		this.imageLoc = imageLoc;
	}

	public static void setItemDTO(ItemDTO itemDTO) {
		ItemDTO.itemDTO = itemDTO;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageLoc == null) ? 0 : imageLoc.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
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
		ItemDTO other = (ItemDTO) obj;
		if (imageLoc == null) {
			if (other.imageLoc != null)
				return false;
		} else if (!imageLoc.equals(other.imageLoc))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemDTO [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", imageLoc=" + imageLoc
				+ ", shopId=" + shopId + "]";
	}
}
