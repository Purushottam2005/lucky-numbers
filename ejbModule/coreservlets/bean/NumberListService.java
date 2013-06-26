package coreservlets.bean;

import java.util.List;
import javax.ejb.*;

@Remote
public interface NumberListService {
  public List<Double> getNumbers(int size, double range);
}