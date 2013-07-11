package com.ibytecode.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity(name="feed")
public class Feed implements Serializable {
	private static final long serialVersionUID = -8084827977542098341L;

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
	
	@OneToMany( mappedBy="feed", fetch = FetchType.LAZY)
	private List<Item> itemList; 
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	  @JoinTable(
	      name="join_feed_user",
	      joinColumns={@JoinColumn(name="feed_id", referencedColumnName="id")},
	      inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
	private List<User> userList	;

//	//FIXME if there is no other constructor, this one can be deleted
//	public Feed() {
//	
//	}

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

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public String getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	


}