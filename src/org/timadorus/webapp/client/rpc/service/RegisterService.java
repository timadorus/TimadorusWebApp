package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * RegisterService interface for GWT-RPC.
 */
@RemoteServiceRelativePath("register")
public interface RegisterService extends RemoteService {
  
  /**
   * Registers a user supplied as dataIn parameter.
   * 
   * @param dataIn The user which shall be registered
   * @return Return value of this.isValid(dataIn)
   */
  String register(User dataIn);
}