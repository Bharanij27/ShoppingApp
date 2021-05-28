package com.utility;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.InvoiceMasterDTO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSDemo{
	public static final String ACCOUNT_SID = "AC2f07c7c57305534c6e17795222118f1b";
	public static final String AUTH_TOKEN = "5d6857ef55f0f747c2ab3da56322fa4c";

	public void sendSMS(HttpServletRequest req, HttpSession session) {
		try {
			System.out.println("on messag");
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
			String smsNum = req.getParameter("smsto");
			List<InvoiceMasterDTO> invoices = (List<InvoiceMasterDTO>) session.getAttribute("smsData");
			
			Message message = Message.creator(new PhoneNumber("+91" + smsNum),
					new PhoneNumber("+13236414774"), 
					createMessage(invoices)).create();
		
			System.out.println(message.getSid());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String createMessage(List<InvoiceMasterDTO> invoices) {
		String messages = "Hi Your invoice Data";

		for (Iterator<InvoiceMasterDTO> iterator = invoices.iterator(); iterator.hasNext();) {
			InvoiceMasterDTO in = (InvoiceMasterDTO) iterator.next();
			messages += " Invoice Id : " + in.getInvoiceId() + " Date : "+ in.getInvoiceDate();
		}
		return messages;
	}
}
