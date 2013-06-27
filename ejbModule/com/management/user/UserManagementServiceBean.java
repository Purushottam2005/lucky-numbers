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
	
	
	
	//public List<Item> getAllItemNewsForUser(String userEmail) {
	public String getAllItemNewsForUser(String userEmail) {
		System.out.println("Inside getAllItemNewsForUser, userEmail : " + userEmail);
		//List<Item> itemList = new ArrayList<Item>();
		//TODO generally, we use userEmail to identify user whoose item news we' d like to fetch
		// Now for simplicity we can use the hard coded value;
		
		String hardCodedUserEmail  = "jjoinme@bk.ru";
		//FIXME This piece of code is repeated with login
		//TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.email = :email", User.class);
		
//		
//		User currentUser = entityManager.find(User.class, hardCodedUserEmail);
//		System.out.println("Simple check, if i could get user like this: " + currentUser);
//		
		
		
		
//		TypedQuery<Feed> queryJoinTables = entityManager.
//				createQuery("SELECT f FROM feed f, user u, join_feed_reader WHERE  u.email = :email AND f.id= u.id", Feed.class);
//				//createQuery("SELECT f FROM feed f, user u WHERE  u.email = :email AND f.id= u.id", Feed.class); 
				//createQuery("SELECT f FROM feed f, user u WHERE f.id= u.id", Feed.class); 
		
		
		TypedQuery<Feed> queryJoinTables = entityManager.
				createQuery("Select f from feed f join f.userList i where i.id = :id", Feed.class);
				//createQuery("Select f from feed f join f.userList i where i.user_id = :id", Feed.class);
		
		
		
		List<Feed> feedList = new ArrayList<Feed>();
		long hardCodedId = 1L;
		feedList = queryJoinTables.setParameter("id", hardCodedId).getResultList();
		
		for (Feed feed : feedList) {
			System.out.println(feed);
		}
		
		//User user = query.setParameter("email", hardCodedUserEmail).getSingleResult();
		
		
		
		
		
		
		return "itemList";
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
