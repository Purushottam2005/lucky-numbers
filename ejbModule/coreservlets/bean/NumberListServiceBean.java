package coreservlets.bean;

import javax.ejb.*;

import org.jboss.ejb3.annotation.RemoteBinding;

import java.util.*;

// In JBoss, the default JNDI name would be "NumberServiceListBean/remote".
// In Glassfish, the default name would be "coreservlets.bean.NumberServiceList".
// But since I use @Stateless(mappedName="NumberListCreator") instead of just @Stateful,
// I can use context.lookup("NumberListCreator") in clients on both app servers.

//@Stateless
//@RemoteBinding(jndiBinding ="NumberListCreator")
@Stateless(mappedName="NumberListCreator")
public class NumberListServiceBean implements NumberListService {
  @EJB private NumberService numService;
  
  public List<Double> getNumbers(int size, double range) {
    List<Double> nums = new ArrayList<Double>();
    for(int i=0; i<size; i++) {
      nums.add(numService.getNumber(range));
    }
    return(nums);
  }
}
