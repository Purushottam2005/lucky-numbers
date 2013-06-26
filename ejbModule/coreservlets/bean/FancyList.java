package coreservlets.bean;

import java.util.List;
import javax.ejb.*;

@Remote
public interface FancyList {
  public void initializeNumbers(int size, double range);
  public List<Double> getNumbers();
  public double getSum();
  public double getSmallest();
  public double getLargest();
  public void removeList();
}
