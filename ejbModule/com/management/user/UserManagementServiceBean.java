package com.management.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ibytecode.entities.Feed;
import com.ibytecode.entities.Item;
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
	
	
	//TODO 
	//javax.persistence.NoResultException: No entity found for query
	
	
	//public List<Item> getAllItemNewsForUser(String userEmail) {
	public String getAllItemNewsForUser(String userEmail) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("Inside getAllItemNewsForUser, userEmail : " + userEmail);
		//List<Item> itemList = new ArrayList<Item>();
		//TODO generally, we use userEmail to identify user whoose item news we' d like to fetch
		// Now for simplicity we can use the hard coded value;

		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class);
		User user = query.setParameter("email", userEmail).getSingleResult();
		
		TypedQuery<Item> queryJoinTables = entityManager.
				createQuery("Select i from item i join i.feed f join f.userList l where l.id = :id", Item.class);
		 List<Item>  itemList = queryJoinTables.setParameter("id", user.getId()).getResultList();
		
		for (Item item : itemList) {
		 System.out.println(item);	
		}
		System.out.println("-----------------------------------------------------------");
		return "itemList";
	} 
	
	
	public String getAllFeedsForUser(String userEmail) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("Inside getAllFeedsForUser, userEmail : " + userEmail);
		//List<Item> itemList = new ArrayList<Item>();
		//TODO generally, we use userEmail to identify user whoose item news we' d like to fetch
		// Now for simplicity we can use the hard coded value;
		//String hardCodedUserEmail  = "jjoinme@bk.ru";
		//FIXME This piece of code is repeated with login
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class);
		User user = query.setParameter("email", userEmail).getSingleResult();
		
		TypedQuery<Feed> queryJoinTables = entityManager.
				createQuery("Select f from feed f join f.userList l where l.id = :id", Feed.class);
				//createQuery("Select f from feed f join f.userList i where i.user_id = :id", Feed.class);

		//long hardCodedId = 1L;
		//feedList = queryJoinTables.setParameter("id", hardCodedId).getResultList();
		 List<Feed>  feedList = queryJoinTables.setParameter("id", user.getId()).getResultList();
		
		for (Feed feed : feedList) {
			System.out.println(feed);
		}
		System.out.println("-----------------------------------------------------------");
		return "feedList";
	} 
	
	
	
	
	
	
	
	
	/*
	 * 	public BlogEntry saveBlogEntry(BlogEntry blogEntry) {
		em.persist(blogEntry);
		return blogEntry;
	}
 
	public List<BlogEntry> findBlogEntries() {
		final Query query = em.createQuery("SELECT b FROM BlogEntry b ORDER BY b.created DESC");
		List<BlogEntry> entries = query.getResultList();
		if (entries == null) {
			entries = new ArrayList<BlogEntry>();
		}
		return entries;
	}
 
	public void deleteBlogEntry(BlogEntry blogEntry) {
		blogEntry = em.merge(blogEntry);
		em.remove(blogEntry);
	}
	
	 */
	
	
}
