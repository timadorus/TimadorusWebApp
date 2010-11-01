package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

//Interface for Asyncronous "register"-Method-Calls (RPC-calls) between client and server
public interface RegisterServiceAsync {

  void register(User data, AsyncCallback<String> asyncCallback);

}