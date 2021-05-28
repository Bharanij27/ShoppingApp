package com.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.ItemDTO;
import com.service.ItemServiceImpl;

@Component
public class ShopComponent {
	
	@Autowired
	private ItemServiceImpl itemService;

	public ItemServiceImpl getItemService() {
		return itemService;
	}

	public void setItemService(ItemServiceImpl itemService) {
		this.itemService = itemService;
	}
	
	public ItemDTO findById(String id) {
		return itemService.findItemById(id);
	}
	
	public List<ItemDTO> findAllByShop(String shopName) {
		return itemService.findItemByShopId(shopName);
	}
	
}
