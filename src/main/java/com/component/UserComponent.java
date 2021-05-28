package com.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.model.UserDTO;
import com.service.UserService;

@Component
public class UserComponent {
	@Autowired
	private UserService userService;

	public UserService getMyservice() {
		return userService;
	}

	public void setMyservice(UserService myservice) {
		this.userService = userService;
	}
	
	public void addUser(String uname, String upass) {
		UserDTO user = UserDTO.getUserDTO();
		user.setUserName(uname);
		user.setPassword(upass);
		user.setFlag(0);
		userService.createUser(user);
	}

	public boolean validateUser(String uname, String upass) {
		UserDTO orgData = userService.findUserByUserName(uname);
		if(orgData.getUserName().equals(uname) && orgData.getPassword().equals(upass)) {
			orgData.setFlag(1);
			userService.updateUser(orgData);
			return true;
		}
		return false;
	}
	
	public void logoutUser(String uname) {
		UserDTO user = userService.findUserByUserName(uname);
		user.setFlag(0);
		userService.updateUser(user);
	}

	public boolean isUserExists(String uname) {
		return userService.checkUserName(uname);
	}
}
