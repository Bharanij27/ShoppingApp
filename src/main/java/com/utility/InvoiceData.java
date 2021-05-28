package com.utility;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.component.ShopComponent;
import com.model.ItemDTO;

public class InvoiceData extends TagSupport{
	
	ShopComponent component;
	
	public ShopComponent getComponent() {
		return component;
	}

	public void setComponent(ShopComponent shopComponent) {
		this.component = shopComponent;
	}	
	@Override
	public int doStartTag() throws JspException {				
		JspWriter out = pageContext.getOut();
		HttpSession session = pageContext.getSession();
		Enumeration<String> e=session.getAttributeNames();
		float grandTotal = 0;
		
		while(e.hasMoreElements()){
			String name=e.nextElement();
			String value=(String)session.getAttribute(name);
			if(!name.equals("uname") && !name.equals("nextPage") && !name.equals("invoiceId") && !value.equals("0") && !name.equals("sendCustomer")){
				ItemDTO item = component.findById(name);
				if(item !=null && item.getItemid() != null){
					float totalPrice = Integer.parseInt(value) * item.getPrice();
					grandTotal += totalPrice;
					try {
						out.print("<tr> <td> <label> " + item.getItemName() + " </label> </td> <td>"
								+ "<span> " + value + " </span>	</td><td>"
								+ "<span> " + totalPrice + "</span>	</td> </tr> ");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		try {
			out.print("<tr> <td> </td> <td id='gtotal-label'> Grand Total </td> <td> " + grandTotal + "</td> </tr>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return super.doStartTag();
	}
}
