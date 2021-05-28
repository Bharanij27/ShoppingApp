package com.control;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CustomSessionListener implements HttpSessionListener{
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		System.out.println("Session destroyed");
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(5 * 60);
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
		System.out.println("session destroyed...");

		LoginControlller login = new LoginControlller();
    	HttpSession session = se.getSession();
    	
    	Object name=session.getAttribute("uname");
    	if(name!=null) {
    		login.logout(session);
    	}
    	login.showLoginForm();
	}
}
