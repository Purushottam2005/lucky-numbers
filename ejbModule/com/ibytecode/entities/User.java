package com.ibytecode.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "user")
public class User implements Serializable {
	

	private static final long serialVersionUID = 3654205611614165482L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
//	@Column
//	@OneToMany(mappedBy="feedReader")
//	private List<Feed> feedList;
//	

	//@ManyToMany(mappedBy="projects")
	//  private List<Employee> employees;
	@ManyToMany(mappedBy="userList")
	private List<Feed> feedList;
	
	public List<Feed> getFeedList() {
		return feedList;
	}

	public void setFeedList(List<Feed> feedList) {
		this.feedList = feedList;
	}

	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
//	public List<Feed> getFeedList() {
//		return feedList;
//	}
//
//	public void setFeedList(List<Feed> feedList) {
//		this.feedList = feedList;
//	}
	
	@Override
	public String toString() {
		return " [email] : " + email +
			   " [password] : "  +password +
			   " [firstName] : " + firstName + 
			   " [lastName] : " + lastName
			   ;
	}
	
}