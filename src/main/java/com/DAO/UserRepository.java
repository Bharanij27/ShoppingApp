package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.UserDTO;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDTO, Integer>{
	
	public UserDTO findByUserId(int id);
	
	public UserDTO findByUserName(String username);

	public boolean existsByUserName(String uname);
}
