package org.timadorus.webapp.client.service;

import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceAsync<T, U> {

  void execute(Action<T> action, AsyncCallback<Response<U>> callback);

}
