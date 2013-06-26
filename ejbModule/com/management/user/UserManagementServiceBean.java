package com.management.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ibytecode.entities.Feed;
import com.ibytecode.entities.User;

@Stateless
public class UserManagementServiceBean implements UserManagementService, Serializable{

	private static final long serialVersionUID = -7947779666246162567L;

	//@Resource 
	@PersistenceContext(unitName = "JPADB")
	    private EntityManager entityManager;
	
	@Override
	public void register(String email, String password, String firstName, String lastName) {
		System.out.println("inside UserManagementServiceBean,REGISTER,  email: "  + email + " password: " + password + " firstName : " + firstName + " lastName: "+ lastName);		
		entityManager.persist(new User(email, password, firstName, lastName)); 
		entityManager.flush();
		System.out.println("Insert is completed...");
	}

	
	public String fetchData(int feedId) {
	//	Project p = entityManager.find(Project.class, projectNumber );
	//	return (p != null)? p.toString():"No project with project No: " + projectNumber;
		Feed feed = entityManager.find(Feed.class, feedId);
		return (feed != null)? feed.toString():"No project with project No: " + feedId;
	}


	@Override
	public boolean login(String email, String password) {
		System.out.println("inside UserManagementServiceBean, LOGIN, email: "  + email + " ,password: " + password);
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class);
		User user = query.setParameter("email", email).getSingleResult();
		System.out.println("user :" + user);
		return user != null && password.equals(user.getPassword());
	}
	
	
	//public String getAllUsers() {
	public List<User> getAllUsers() {
		System.out.println("inside UserManagementServiceBean, getAllUsers");
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u", User.class);
		
		List<User> userList = new ArrayList<User>();
		userList = query.getResultList();
		for (User user : userList) {
			System.out.println(user);
		}
		
		//TODO implement the method, admin usage
		return userList;
	}
	
}
