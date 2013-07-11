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
	@Column
	private String title;
	@Column
	private String link;
	@Column
	private String description;
	
	@Column(name="publication_date")
	private String pubDate;
	@Column
	private String guid;
	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	@Column
	private String category;
	
	// 
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date				created = new Date();
	
	//@Column(name="feed_id")
	
	//@ManyToOne(fetch=FetchType.LAZY) // Failed with $$_javassist_1
	@ManyToOne(fetch=FetchType.EAGER) //!!!!
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



	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public String getGuid() {
		return guid;
	}

	public String getCategory() {
		return category;
	}

	public Feed getFeed() {
		return feed;
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
