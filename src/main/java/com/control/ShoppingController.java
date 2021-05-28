package com.control;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.component.ShopComponent;

@Controller
@RequestMapping("/shop")
public class ShoppingController {
	
	@Autowired
	private ShopComponent shopComponent;

	public ShopComponent getShopComponent() {
		return shopComponent;
	}

	public void setShopComponent(ShopComponent shopComponent) {
		this.shopComponent = shopComponent;
	}
	
	@RequestMapping(value="toShopping", method = RequestMethod.POST)
	public String goToShop(HttpSession session, ModelMap model, HttpServletRequest req) {
		String nextPage = req.getParameter("nextPage");
		updateCart(session, req);
		model.addAttribute("component", shopComponent);
		if(req.getParameter("shopid") != null && req.getParameter("shopid").equals("shop1")) {
			newPurchaseData(session);
		}
		return nextPage;
	}
	
	private void updateCart(HttpSession session, HttpServletRequest req){
		Enumeration<String> e = req.getParameterNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			String value = req.getParameter(name);
			
			if(name.equals("formid")||name.equals("shopid") || value.equals("0")) {
			}
			else {
				session.setAttribute(name, value);
			}	
		}
	}
	
	
	private void newPurchaseData(HttpSession session) {
		String invoiceId = UUID.randomUUID().toString().substring(0, 8);
		String customerId = UUID.randomUUID().toString().substring(0, 8);
		session.setAttribute("invoiceId", invoiceId);
		session.setAttribute("customerId", customerId);	
	}
	
}
