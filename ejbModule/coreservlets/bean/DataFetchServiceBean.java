package coreservlets.bean;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ibytecode.entities.Feed;

@Stateless
public class DataFetchServiceBean implements DataFetchService, Serializable {

	private static final long serialVersionUID = 1236675032564845452L;

	 @PersistenceContext(unitName = "JPADB")
	    private EntityManager entityManager;
	
	@Override
	//public String fetchData(int projectNumber) {
	public String fetchData(int feedId) {
	//	Project p = entityManager.find(Project.class, projectNumber );
	//	return (p != null)? p.toString():"No project with project No: " + projectNumber;
		Feed feed = entityManager.find(Feed.class, feedId);
		return (feed != null)? feed.toString():"No project with project No: " + feedId;
	}
	
	
	
	
}
