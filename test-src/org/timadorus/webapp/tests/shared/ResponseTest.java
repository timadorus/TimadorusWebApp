package org.timadorus.webapp.tests.shared;

import org.junit.Assert;
import org.junit.Test;
import org.timadorus.webapp.shared.Response;

public class ResponseTest {

  @Test
  public void testResponse() {
    Response<String> theResponse = new Response<String>();
    Assert.assertNull("Result should be null", theResponse.getResult());
    
    final String theResult = "huhu";
    theResponse = new Response<String>(theResult);
    Assert.assertEquals("Result should be " + theResult, theResult, theResponse.getResult());
  }
  
}
