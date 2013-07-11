package com.ibytecode.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 3654205611614165482L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@ManyToMany(mappedBy="userList", fetch = FetchType.LAZY)
	private List<Feed> feedList;
	// FIXME private Set<Feed> feedList; ???
	
	//@NotNull
	//@Size(min = 10, max = 100)
	//private String				title;
	
//	@Lob
//	@NotNull
//	@Size(min = 300, max = 4000)
//	private String				content;
	
	/*
	 * @Lob // <- The text from a blog entry might take some space so we’re predicting that with the @LoB annotation
	private String				content;
 
	private String				author;
 
	@Temporal(TemporalType.TIMESTAMP) // <- We want to save the creation date of a blog entry as a timestamp and not a date – that’s why we add @Temporal(TemporalType.TIMESTAMP)
	private Date				created = new Date();
 
	@PrePersist			// <- To set the creation date on our first persist we’re using @PrePersist
	private void onCreate() {
		created = new Date();
	}
	 */
	
	
	
	public List<Feed> getFeedList() {
		return feedList;
	}

	public void setFeedList(List<Feed> feedList) {
		this.feedList = feedList;
	}
	
	public User() {
	
	}
	

	public User(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

//	public User(String email, String password) {
//		this.email = email;
//		this.password = password;
//	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	
	@Override
	public String toString() {
		return " [email] : " + email +
			   " [password] : "  +password +
			   " [firstName] : " + firstName + 
			   " [lastName] : " + lastName
			   ;
	}
}