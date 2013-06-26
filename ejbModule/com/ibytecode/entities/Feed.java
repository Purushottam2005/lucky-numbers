package com.ibytecode.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//| id | title| link| publication_date| author | description

@Entity(name="feed")
public class Feed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String link;
	
	@Column(name="publication_date")
	private String pubDate;
	
	@Column
	private String author;
	
	@Column
	private String description;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="feedList")
//	private User feedReader;

	
	@OneToMany(mappedBy="feed")
	private List<Item> itemList; 
	
	
	@ManyToMany
	  @JoinTable(
	      name="JOIN_FEED_READER",
	      joinColumns={@JoinColumn(name="feed_id", referencedColumnName="id")},
	      inverseJoinColumns={@JoinColumn(name="reader_id", referencedColumnName="id")})
	private List<User> userList	;
	//private List<Project> projects;
	
	
	
	public List<Item> getItemList() {
		return itemList;
	}


	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}


	public List<User> getUserList() {
		return userList;
	}


	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	public Feed() {
		super();
	}


	@Override
	public String toString() {
		return 	 "++FEED_OBJECT++ ==> " + "[id] : " +id
				+ "[title] : " +title
				+ "[link] : " +link
				+ "[description] : " +description
				+ "[author] :" + author
				+ "[pubDate] : " +pubDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getPubDate() {
		return pubDate;
	}


	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

//	public User getUser() {
//		return feedReader;
//	}
//
//
//	public void setUser(User feedReader) {
//		this.feedReader = feedReader;
//	}
	
	
	
//	public void setField(String name, String value) {
//		System.out.println("[[setField]]:"+name+" / "+value);
//		try {
//			this.getClass().getDeclaredField(name).set(this, value);
//		} catch (IllegalArgumentException e) {
//			System.err.println("Couldn't set property : " + name + " with value: " + value  + ".Error: " + e);
//		} catch (SecurityException e) {
//			System.err.println("Couldn't set property : " + name + " with value: " + value  + ".Error: " + e);
//		} catch (IllegalAccessException e) {
//			System.err.println("Couldn't set property : " + name + " with value: " + value  + ".Error: " + e);
//		} catch (NoSuchFieldException e) {
//			System.err.println("Couldn't set property : " + name + " with value: " + value  + ".Error: " + e);
//		}
//	}
}