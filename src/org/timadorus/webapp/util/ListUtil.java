package org.timadorus.webapp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Just a small helper class. 
 * 
 * @author rt
 */
public final class ListUtil {
  private ListUtil() { }

  /**
   * Collects some elements of a given list or just some attributes of them. 
   * Usage: 
   * 
   * ListUtil.collectListItems(new ListCollector<Type of Input, Type of Output>() {
   *   @Override
   *   public Type in Output collect(Type in Input aCollectableObject) {
   *     return aCollectableObject;
   *   }
   *   @Override
   *   public boolean isCollectable(Type of Input aCollectableObject) {
   *     return true / false;
   *   }
   *   
   * }, myList);
   * 
   * @param aListCollector
   * @param aList
   * @return
   */
  public static <T, U> List<U> collectListItems(ListCollector<T, U> aListCollector, List<T> aList) {
    List<U> theReturnList = new ArrayList<U>();
    
    for (T theItem : aList) {
      if (aListCollector.isCollectable(theItem)) {
        theReturnList.add(aListCollector.collect(theItem));
      }
    }
    
    return theReturnList;
  }
  
  public abstract static class DefaultListCollector<T, U> implements ListCollector<T, U> {

    @Override
    public boolean isCollectable(T aCollectableObject) {
      return true;
    }
    
  }
  
  public interface ListCollector<T, U> {
    public boolean isCollectable(T aCollectableObject);
    
    public U collect(T aCollectableObject);
  }
}
