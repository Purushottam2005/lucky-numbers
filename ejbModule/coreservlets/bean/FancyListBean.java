package coreservlets.bean;

import java.util.*;
import javax.ejb.*;

import org.jboss.ejb3.annotation.RemoteBinding;

// In JBoss, the default name would be "FancyListBean/remote".
// In Glassfish, the default name would be "coreservlets.bean.FancyList".
// But since I use @Stateful(mappedName="cool-number-list") instead of just @Stateful,
// I can use context.lookup("cool-number-list") in clients on both app servers.

//@Stateful
//@RemoteBinding(jndiBinding ="cool-number-list")

@Stateful(mappedName="cool-number-list")
public class FancyListBean implements FancyList {
  @EJB 
  private NumberListService service;
  
  private List<Double> nums = new ArrayList<Double>();
  
  public void initializeNumbers(int size, double range) {
    nums = service.getNumbers(size, range);
  }
  
  public List<Double> getNumbers() {
    return(nums);
  }
  
  public double getSum() {
    double sum = 0;
    for(double num: nums) {
      sum = sum + num;
    }
    return(sum);
  }
  
  public double getSmallest() {
    return(Collections.min(nums));
  }
  
  public double getLargest() {
    return(Collections.max(nums));
  }

  @Remove
  public void removeList() {
    nums = new ArrayList<Double>();
  }
}
