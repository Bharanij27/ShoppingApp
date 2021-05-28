package com.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.InvoiceCrossItemDTO;
import com.model.InvoiceTransDTO;
import com.model.ItemDTO;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<ItemDTO, String>{
	public List<ItemDTO> findByShopId(String shopId);
	public ItemDTO findByItemId(String id);

//	@Query("SELECT new com.model.InvoiceCrossItemDTO(it.itemName, ins.quantity, it.itemPrice) from item it"
//			+ ", invoiceTrans ins where ins.invoiceId=?1")
//	public List<InvoiceCrossItemDTO> fetchInvoiceTransItemCrossJoin(String invoiceId);
}
