package com.management.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ibytecode.entities.Feed;
import com.ibytecode.entities.Item;
import com.ibytecode.entities.User;
// example string
//String email_query ="SELECT u FROM user u WHERE u.email = :email" ;

@Stateless
public class UserManagementServiceBean implements UserManagementService, Serializable{
	
	private static final long serialVersionUID = -7947779666246162567L;
	private static final String USER_BY_EMAIL_QUERY = "SELECT u FROM user u WHERE u.email = ?";
	private static final String FEED_LIST_OF_USER_QUERY = "Select f from feed f join f.userList l where l.id = :id";
	private static final String ITEM_LIST_OF_USER_QUERY = "Select i from item i join i.feed f join f.userList l where l.id = :id";

	//@Resource 
	@PersistenceContext(unitName = "JPADB")
	    private EntityManager entityManager;
	
	@Override
	public void register(String email, String password, String firstName, String lastName) {
		System.out.println("inside UserManagementServiceBean, REGISTER,  email: "  + email + " password: " + password + " firstName : " + firstName + " lastName: "+ lastName);		
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
	public User login(String email, String password) {
		System.out.println("inside UserManagementServiceBean, LOGIN, email: " + email + " ,password: " + password);
		User user = fetchUserByParameter(email, USER_BY_EMAIL_QUERY);
		return user == null || isPasswordWrong(user, password) ? null : user;
	}
	
	
	private boolean isPasswordWrong(User user, String password) {
		return !password.equals(user.getPassword());
	}


	public boolean isUserRegisteredAlready(String email) {
		System.out.println("isUserRegisteredAlready, email: " + email);
//		User user = fetchUserByEmail(email);
		User user = fetchUserByParameter(email, USER_BY_EMAIL_QUERY);
		System.out.println("user :" + user);
		return user != null;
	}
	
	
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


	
	//FIXME This piece of code is repeated with login
	
	//TODO refactor getAllItemNewsForUser, getAllFeedsForUser with generics 

//	public List<Item> getAllItemNewsForUser(String email) {
//		System.out.println("Inside getAllItemNewsForUser, userEmail : " + email);
//		User user = fetchUserByParameter(email, USER_BY_EMAIL_QUERY);
//		TypedQuery<Item> queryJoinTables = entityManager.createQuery(ITEM_LIST_OF_USER_QUERY, Item.class);
//		List<Item> itemList = queryJoinTables.setParameter("id", user.getId()).getResultList();
//		return itemList;
//	} 
//
//	public List<Feed> getAllFeedsForUser(String email) {
//		System.out.println("Inside getAllFeedsForUser, userEmail : " + email);
//		User user = fetchUserByParameter(email, USER_BY_EMAIL_QUERY);
//		TypedQuery<Feed> queryJoinTables = entityManager.createQuery(FEED_LIST_OF_USER_QUERY, Feed.class);
//		List<Feed> feedList = queryJoinTables.setParameter("id", user.getId()).getResultList();
//		return feedList;
//	}

	
	public List<Item> getAllItemNewsForUser(String email) {
		return getListOfInterst(fetchUserByParameter(email, USER_BY_EMAIL_QUERY).getId(), ITEM_LIST_OF_USER_QUERY, Item.class);
	} 

	public List<Feed> getAllFeedsForUser(String email) {
		return getListOfInterst(fetchUserByParameter(email, USER_BY_EMAIL_QUERY).getId(), FEED_LIST_OF_USER_QUERY, Feed.class);
	}	

	private <T> List<T> getListOfInterst(long id, String query, Class<T> arg)  {
		return entityManager.createQuery(query, arg).setParameter("id", id).getResultList();
	}

	private User fetchUserByParameter(String parameter, String query) {
		try{
	        return entityManager.createQuery(query, User.class).setParameter(1, parameter).getSingleResult();
	    } catch(NoResultException e) {
	    	System.err.println("No user found with parameter: " + parameter + " and query: " + query + ", message: " + e);
	    	return null;
	    }
	} 
}

//	private User fetchUserByEmail(String email) {
//		//return entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class).setParameter("email", email).getSingleResult();
//		return entityManager.createQuery(USER_BY_EMAIL_QUERY, User.class).setParameter(1, email).getSingleResult();
//	} 
//	