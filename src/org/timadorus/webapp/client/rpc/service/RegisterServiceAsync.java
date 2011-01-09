package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * RegisterServiceAsync interface for GWT-RPC.
 */
public interface RegisterServiceAsync {

  /**
   * Wrapper method for RPC communication.
   * Registers a user supplied as dataIn parameter.
   * 
   * @param dataIn The user which shall be registered
   * @param callback The callback class which will be triggered if the service has finished
   */
  void register(User dataIn, AsyncCallback<String> callback);

}