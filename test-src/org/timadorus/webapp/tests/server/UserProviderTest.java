package org.timadorus.webapp.tests.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.client.service.ServiceAsync;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.server.rpc.service.UserServiceImpl;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class UserProviderTest {

  
  private final ServiceAsync<User, String> registUser = GWT.create(Service.class);
  private final ServiceAsync<User, Integer> updateUser = GWT.create(Service.class);
  private final ServiceAsync<User, User> getUser = GWT.create(Service.class);
  private final ServiceAsync<User, String> deleteUser = GWT.create(Service.class);
  
  private User user1;
  private UserServiceImpl  userService;
  private String activisonCode;
  private int updateResult;
  private String deleteResult;
  
  @Before
  public void setUp() throws Exception {
    userService = new UserServiceImpl();
   
       
    user1 = new User("vorname", "nachname", "1.1.1970", "test@mail.org", "testuser01", "password");
    
    
    Action<User> action = new Action<User>(ServiceType.REGISTER, user1);

    AsyncCallback<Response<String>> response = new AsyncCallback<Response<String>>() {

      @Override
      public void onFailure(Throwable arg0) {
        System.out.println("onFailure");
      
      }

     

      @Override
      public void onSuccess(Response<String> result) {
        // TODO Auto-generated method stub
        activisonCode = result.getResult();
      }
    };
    registUser.execute(action, response);
    
    
    
    
   }
  
  
  
  @Test
  public void test_getUser() {
    Long userid = user1.getId();
    String vorname = "vorname_test";
    String nachname = "nachname_test";
    String email = "email@test.org";
    String gb = "01.01.1971";
    String pw = "pw_test";
    
    
    //chance user data
    user1.setVorname(vorname);
    user1.setNachname(nachname);
    user1.setEmail(email);
    user1.setGeburtstag(gb);
    user1.setPassword(pw);
    
    //apply user data chance
    Action<User> action = new Action<User>(ServiceType.UPDUSER, user1);

    AsyncCallback<Response<Integer>> responseUpdateUser = new AsyncCallback<Response<Integer>>() {

      @Override
      public void onFailure(Throwable arg0) {
        System.out.println("onFailure");
        
      }

      @Override
      public void onSuccess(Response<Integer> result) {
            updateResult = result.getResult();
      }
    };
    updateUser.execute(action, responseUpdateUser);
    
    //update user
    action = new Action<User>(ServiceType.GETUSER, user1);

    AsyncCallback<Response<User>> responseGetUser = new AsyncCallback<Response<User>>() {

      @Override
      public void onFailure(Throwable arg0) {
        System.out.println("onFailure");
        

      }

      @Override
      public void onSuccess(Response<User> result) {
        user1 = result.getResult();
      }
    };
    
   getUser.execute(action, responseGetUser);
    
    Assert.assertEquals(userid, user1.getId());
    Assert.assertEquals(vorname, user1.getVorname());
    Assert.assertEquals(nachname, user1.getNachname());
    Assert.assertEquals(email, user1.getEmail());
    Assert.assertEquals(gb, user1.getGeburtstag());
    Assert.assertEquals(pw, user1.getPassword());
      }
  
  
  public void test_deleteUser() {
    
    //delete user
    Action<User> action = new Action<User>(ServiceType.DELUSER, user1);
    
    AsyncCallback<Response<String>> responseDeleteUser = new AsyncCallback<Response<String>>() {

      @Override
      public void onFailure(Throwable arg0) {
        System.out.println("onFailure");
        

      }

      @Override
      public void onSuccess(Response<String> result) {
        // TODO Auto-generated method stub
        deleteResult = result.getResult();
      }
    };
    
    deleteUser.execute(action, responseDeleteUser);
    
    
    
    
    
    //update user
    action = new Action<User>(ServiceType.GETUSER, user1);

    AsyncCallback<Response<User>> responseGetUser = new AsyncCallback<Response<User>>() {

      @Override
      public void onFailure(Throwable arg0) {
        System.out.println("onFailure");
        

      }

      @Override
      public void onSuccess(Response<User> result) {
        // TODO Auto-generated method stub
        user1 = result.getResult();
      }
    };
    
    getUser.execute(action, responseGetUser);
    

    
    Assert.assertEquals(null, user1);
    
  }
  
  
  
  
  
  

}
