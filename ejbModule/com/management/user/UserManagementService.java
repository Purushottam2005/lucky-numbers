package com.management.user;

import java.util.List;

import javax.ejb.Remote;

import com.ibytecode.entities.Feed;
import com.ibytecode.entities.Item;
import com.ibytecode.entities.User;

@Remote
public interface UserManagementService {
	//public void register(String email, String password, String firstName, String lastName);
	public void register(User user);

	
	//public User login(String email, String password);
	public User login(String email);
	
	public List<User> getAllUsers();
	public List<Feed> getAllFeedsForUser(String email);
	public List<Item> getAllItemNewsForUser(String email);
	
//	public Feed getAllFeedsForUser(String email);
//	public List<String> getAllFeedsForUser(String email);
//	public Item getAllItemNewsForUser(String email);
}
