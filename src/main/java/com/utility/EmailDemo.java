package com.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmailDemo{
	
	private void addAttachment(Multipart multipart, String filename) {
		try {
			DataSource source = new FileDataSource(filename);
		    BodyPart messageBodyPart = new MimeBodyPart();        
		    messageBodyPart.setDataHandler(new DataHandler(source));
		    messageBodyPart.setFileName(filename);
		    multipart.addBodyPart(messageBodyPart);	    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(HttpServletRequest req, HttpSession session) {
		try {
			Properties mailServerProperties;
			String recipient = req.getParameter("emailto");
			
			ArrayList<String> filePath  = (ArrayList<String>)session.getAttribute("InvoicePath");
			
			if(filePath == null || filePath.size() == 0) {
				System.out.println("No file available");
			}
		
			// Setup Server Properties
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			
			// Setup mail session
			//getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			
			Session getMailSession = Session.getInstance(mailServerProperties,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("bharanij27@gmail.com", "sandhiya@143");
						}
					});
			
			MimeMessage generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			generateMailMessage.setSubject("Invoice");
			
			BodyPart messageBodyPart1 = new MimeBodyPart();  
			messageBodyPart1.setText("PFA Invoices in both excel and pdf format");
			
			Multipart multipart = new MimeMultipart();  
			multipart.addBodyPart(messageBodyPart1);  
			
			for (int i = 0; i < filePath.size(); i++) {
				addAttachment(multipart, filePath.get(i));
			}
			
			//6) set the multiplart object to the message object  
			generateMailMessage.setContent(multipart );
			
			// Send Email
			Transport transport = getMailSession.getTransport("smtp");
			
			
			boolean isSuccess=true;
			try {
				// email and password goes here
				transport.connect("smtp.gmail.com", "bharanij27@gmail.com", "sandhiya@143");
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			} catch(Exception e) {
				e.printStackTrace();
				isSuccess=false;
			} finally {
				transport.close();
				session.removeAttribute("InvoicePath");
			}
			
			System.out.println("Email sent status :"+isSuccess);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
