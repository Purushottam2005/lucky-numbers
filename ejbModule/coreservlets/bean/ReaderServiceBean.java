package coreservlets.bean;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ibytecode.entities.Feed;

@Stateless
public class ReaderServiceBean implements ReaderService, Serializable {

	private static final long serialVersionUID = 1236675032564845452L;

	 @PersistenceContext(unitName = "JPADB")
	    private EntityManager entityManager;
	

	@Override
	public void register(String email, String password) {
		System.out.println("ReaderServiceBean");
		
	}
	
	
	
	
}
