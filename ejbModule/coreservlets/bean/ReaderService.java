package coreservlets.bean;

import javax.ejb.Remote;

@Remote
public interface ReaderService {
	public void register(String email, String password);
}
