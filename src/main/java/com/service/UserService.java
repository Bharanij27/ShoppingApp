package com.service;

import com.model.UserDTO;

public interface UserService {
	public void createUser(UserDTO user);
	public UserDTO findUserByUserName(String name);
	void updateUser(UserDTO user);
	public boolean checkUserName(String uname);
}
