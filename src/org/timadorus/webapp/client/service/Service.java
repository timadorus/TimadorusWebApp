package org.timadorus.webapp.client.service;

import java.io.Serializable;

import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("register")
public interface Service<T extends Serializable , U extends Serializable> extends RemoteService {
  
  
  public Response<T> execute(Action<U> action);
}
