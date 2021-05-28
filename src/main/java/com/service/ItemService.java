package com.service;

import java.util.List;
import java.util.Map;

import com.model.ItemDTO;

public interface ItemService {
	public List<ItemDTO> findItemByShopId(String shopId);
	public ItemDTO findItemById(String itemId);
	Map<String, Object> fetchByInvoiceId(String invoiceId);
}
