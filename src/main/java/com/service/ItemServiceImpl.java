package com.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.ItemRepository;
import com.model.ItemDTO;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemRepository itemRepo;
	 
	@PersistenceContext
	private EntityManager em;
	
	public ItemRepository getItemRepo() {
		return itemRepo;
	}

	public void setItemRepo(ItemRepository itemRepo) {
		this.itemRepo = itemRepo;
	}

	@Override
	public List<ItemDTO> findItemByShopId(String shopId) {
		System.out.println("service " + itemRepo);
		return itemRepo.findByShopId(shopId);
	}
	
	@Override
	public ItemDTO findItemById(String itemId) {
		return itemRepo.findByItemId(itemId);
	}

	@Override
	public Map<String, Object> fetchByInvoiceId(String invoiceId) {
		Map<String, Object> items = new TreeMap<String, Object>();
		Query query = em.createNativeQuery("select it.itemName, ins.quantity, it.itemPrice from item "
				+ "it cross join invoiceTrans ins"
				+ " using(itemId) where ins.invoiceId=?1");
		
		query.setParameter(1, invoiceId);
		
		
		List list = query.getResultList();
		Iterator<Object[]> iter=list.iterator();
		int rowCount=0;
		while(iter.hasNext()) {	
			Object newObj[]=iter.next();
			items.put(invoiceId + " " + rowCount, new Object []{newObj[0], newObj[1], newObj[2]});
			rowCount++;
		}
		return items;
	}
}
