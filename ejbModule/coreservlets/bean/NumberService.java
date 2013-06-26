package coreservlets.bean;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface NumberService {
  public double getNumber(double range);
  public int getServiceMagicNumber();
  public String getServiceMagicString();
  public List<String> retrieveProjectsFromDataBase(); 
  
}


