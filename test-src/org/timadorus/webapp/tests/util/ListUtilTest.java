package org.timadorus.webapp.tests.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.timadorus.webapp.util.ListUtil;
import org.timadorus.webapp.util.ListUtil.ListCollector;
import org.timadorus.webapp.util.ListUtil.DefaultListCollector;

public class ListUtilTest {
  private List<String> myList;
  
  private static final String STRING_1 = "1";
  private static final String STRING_A = "A";
  
  @Before
  public void setUp() {
    myList = new ArrayList<String>();
    
    myList.add(STRING_1);
    myList.add(STRING_A);
  }

  @Test
  public void testCollectListItems() {
    List<String> collectListItems = ListUtil.collectListItems(new DefaultListCollector<String, String>() {
      @Override
      public String collect(String aCollectableObject) {
        return aCollectableObject;
      }
      
    }, myList);
    
    Assert.assertTrue(collectListItems.size() == 2 
        && (collectListItems.get(0).equals(STRING_1) && collectListItems.get(1).equals(STRING_A)));
  }
  
  @Test
  public void testCollectListItems_2() {
    List<String> collectListItems = ListUtil.collectListItems(new ListCollector<String, String>() {
      @Override
      public String collect(String aCollectableObject) {
        return aCollectableObject;
      }

      @Override
      public boolean isCollectable(String aCollectableObject) {
        try {
          Integer.parseInt(aCollectableObject);
          return true;
        } catch (NumberFormatException e) {
          return false;
        }
      }
      
    }, myList);
    
    Assert.assertTrue(collectListItems.size() == 1 && collectListItems.get(0).equals(STRING_1));
  }
  
}
