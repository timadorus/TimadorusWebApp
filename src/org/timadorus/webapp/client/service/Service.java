package org.timadorus.webapp.client.service;

import java.io.Serializable;

import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author sage
 *
 * @param <T> the Response Type
 * @param <U> the Action Type
 */
@RemoteServiceRelativePath("actionService")
public interface Service<T extends Serializable , U extends Serializable> extends RemoteService {
  
  
  /**
   * 
   * @param action the action to execute
   * @return the response
   */
  public Response<T> execute(Action<U> action);
}
