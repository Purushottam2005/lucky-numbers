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
	public void register(User user) {
		System.out.println("inside UserManagementServiceBean, REGISTER,  email: "  + user.getEmail() +
				" password: " + user.getPassword() + " firstName : " + user.getFirstName() + " lastName: "+ user.getLastName());		
		entityManager.persist(user);
		entityManager.flush();
		System.out.println("Insert is completed...");
	}

	//FIXME is there any need in this method ?
	public String fetchData(int feedId) {
	//	Project p = entityManager.find(Project.class, projectNumber );
	//	return (p != null)? p.toString():"No project with project No: " + projectNumber;
		Feed feed = entityManager.find(Feed.class, feedId);
		return (feed != null)? feed.toString():"No project with project No: " + feedId;
	}


	@Override
	// FIXME is there any need in login()- method, when  
	// we have fetchUserByParameter, and password-check we can do on client !
	//public User login(String email, String password) {
	public User login(String email) {
		System.out.println("inside UserManagementServiceBean, LOGIN, email: " + email);
		return fetchUserByParameter(email, USER_BY_EMAIL_QUERY);
		//return user == null || isPasswordWrong(user, password) ? null : user;
	}
	
	
//	private boolean isPasswordWrong(User user, String password) {
//		return !password.equals(user.getPassword());
//	}


	public boolean isUserRegisteredAlready(String email) {
		System.out.println("isUserRegisteredAlready, email: " + email);
//		User user = fetchUserByEmail(email);
		User user = fetchUserByParameter(email, USER_BY_EMAIL_QUERY);
		System.out.println("user :" + user);
		return user != null;
	
		//FIXME If there is no use, NoResultException will be trown
//		try{
//	        return entityManager.createQuery(query, User.class).setParameter(1, parameter).getSingleResult();
//	    } catch(NoResultException e) {
//	    	System.err.println("No user found with parameter: " + parameter + " and query: " + query + ", message: " + e);
//	    	return null;
//	    }
		
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


	
	
	//TODO further refactoring 
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