package org.timadorus.webapp.server.rpc.service;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.server.rpc.service.register.Register;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServiceImpl extends RemoteServiceServlet implements Service {

  /**
   * 
   */
  private static final long serialVersionUID = -2109378966670594123L;

  @Override
  public Response execute(Action action) {
    Response response = null;
    switch (action.getType()) {
    case REGISTER:
      String errorCode = Register.register((User) action.getContent());
      response = new Response<String>(errorCode);
      break;

    default:
      break;
    }
    return response;
  }

}
