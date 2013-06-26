package com.management.user;

import java.util.List;

import javax.ejb.Remote;

import com.ibytecode.entities.User;

@Remote
public interface UserManagementService {
	public void register(String email, String password);
	public boolean login(String email, String password);
	public List<User> getAllUsers();
	//public String getAllUsers() ;
}