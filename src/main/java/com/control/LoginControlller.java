package com.control;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.component.UserComponent;
import com.model.LoginForm;
import com.utility.EmailDemo;
import com.utility.SMSDemo;

@Controller
@RequestMapping("/")
public class LoginControlller {

	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showLoginForm() {
		return new ModelAndView("login", "loginForm", new LoginForm());
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegisterForm() {
		return new ModelAndView("register", "loginForm", new LoginForm());
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHome() {
		return "welcome";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String showError() {
		return "error";
	}
	
	@RequestMapping(value = "/getEmail", method = RequestMethod.GET)
	public String getEmail() {
		return "getInvoice";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session) {
		String uname = (String) session.getAttribute("uname");
		if(uname != null) {			
			userComponent.logoutUser(uname);
			removeSessionAttributes(session);
		}
		return new ModelAndView("login", "loginForm", new LoginForm());
	}
	
	
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("loginForm")LoginForm loginForm, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "error";
        }
        if(userComponent.validateUser(loginForm.getUname(), loginForm.getUpass())) {    
        	removeSessionAttributes(session);
        	session.setAttribute("uname", loginForm.getUname());
        	return "welcome";
        }
        return "login";
        
    }
	
	private void removeSessionAttributes(HttpSession session) {
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println("remving " + name);
			session.removeAttribute(name);
		}
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("loginForm")LoginForm loginForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        String uname = loginForm.getUname();
        System.out.println("name " + uname);
        
        if(userComponent.isUserExists(uname)) {
        	System.out.println("User already exists");
        	String msg = "Username already exists";
        	model.addAttribute("message", msg);
        	return "register";
        }
        
        userComponent.addUser(loginForm.getUname(), loginForm.getUpass());
    	System.out.println("User account created");
        return "login";
    }
	
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public String sendInvoiceEmail(HttpServletRequest req, HttpSession session) {
		EmailDemo emailUtility = new EmailDemo();
		emailUtility.sendMail(req, session);
		return "welcome";
    }
	
	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public String sendInvoiceSMS(HttpServletRequest req, HttpSession session) {
		SMSDemo smsUtility = new SMSDemo();
		smsUtility.sendSMS(req, session);
		return "welcome";
    }
	
	public UserComponent getUserController() {
		return userComponent;
	}

	public void setUserController(UserComponent userComponent) {
		this.userComponent = userComponent;
	}
	
	
}
