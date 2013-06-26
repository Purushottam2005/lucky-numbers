package coreservlets.bean;

import javax.ejb.Remote;

@Remote
public interface DataFetchService {
	public String fetchData(int feedId);
}
