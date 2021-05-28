package com.utility;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.component.ShopComponent;
import com.model.ItemDTO;
import com.service.ItemServiceImpl;

public class PrintData extends TagSupport{
	String shopId;
	ShopComponent component;
	
	public ShopComponent getComponent() {
		return component;
	}

	public void setComponent(ShopComponent shopComponent) {
		this.component = shopComponent;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		List<ItemDTO> i = component.findAllByShop(shopId);
		Iterator<ItemDTO> iterator = i.iterator();		
		System.out.println("On print data" + shopId);
		
		
		while(iterator.hasNext()) 
		{
			ItemDTO item = iterator.next(); 
			if(item.getItemid() != null){
				try {
					out.print("<tr><td> " 
							+ "<img src= " + item.getImageLoc()  + "width=\"100px\" height=\"100px\"> "
							+ "<label> " + item.getItemName() + " </label> </td> <td>"+
							"<div class=\"input-group mb-3\"> "+
							"<input id=\"count\" class=\"form-control\" type=\"number\" min=0 name =" +  item.getItemid() + "  value = 0 > </td> <td></div>"
							+" <span> " + item.getPrice() + " </span> </td> </tr> ");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		
		return super.doEndTag();
	}
}
