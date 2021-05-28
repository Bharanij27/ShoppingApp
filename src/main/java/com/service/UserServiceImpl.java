package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.UserRepository;
import com.model.UserDTO;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	public UserRepository getUserRepository() {
			return userRepo;
		}
		public void setuserRepo(UserRepository userRepo) {
			this.userRepo = userRepo;
		}
	
	@Override
	public void createUser(UserDTO user) {
		userRepo.save(user);
	}
	
	@Override
	public UserDTO findUserByUserName(String name) {
		return userRepo.findByUserName(name);
	}
	
	@Override
	public void updateUser(UserDTO user) {
		userRepo.save(user);
	}
	
	@Override
	public boolean checkUserName(String uname) {
		return userRepo.existsByUserName(uname);
	}
	
}
