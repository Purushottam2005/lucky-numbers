package coreservlets.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class NumberServiceBean implements NumberService, Serializable {
	private static final long serialVersionUID = 1L;

		
	public double getNumber(double range) {
		return (Math.random() * range);
	}

	@Override
	public int getServiceMagicNumber() {
		return 0; //(int) rssSessionBean.getMagicNumber();
	}
	
	public String getServiceMagicString() {
		return ""; //rssSessionBean.getMagicString();
	}

//	public List<FeedMessage> getServiceFeedMessages() {
//		return null;//rssSessionBean.getFeedMessages();
//	}
//
//	public List<FeedMessage> getServiceFeedMessages(String rssURL) {
//		return null;//rssSessionBean.getFeedMessages(rssURL);
//	}

	@Override
	public List<String> retrieveProjectsFromDataBase() {
		// TODO Auto-generated method stub
		return null;
	}

}
