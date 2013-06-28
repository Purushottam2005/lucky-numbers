package com.ibytecode.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "item")
public class Item implements Serializable{

	private static final long serialVersionUID = -7423538024777426730L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String title;
	private String link; 
	private String description;
	
	@Column(name="publication_date")
	private String pubDate;
	private String guid;
	private String category;
	
	// 
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date				created = new Date();
	
	//@Column(name="feed_id")
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="feed_id")
	private Feed feed;
	
	public int getFeedId() {
		return feed.getId();
	}

//	public void setFeedId(int feedId) {
//		this.feed.setId(feedId);
//	}

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

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
	return	" [title]: " + title
		+ " [link ]: " + link
		+ " [category]: " + category
		+ " [description]: " +description
		+ " [pubDate]: " + pubDate
		+ " [guid]: " + guid;
	}
	
}
