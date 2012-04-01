package org.timadorus.webapp.shared;

import java.io.Serializable;

import org.timadorus.webapp.client.service.ServiceType;

public class Action<T> implements Serializable {

  private static final long serialVersionUID = -4707863418808377419L;

  private T content;

  private ServiceType type;

  public Action() {
  }

  public Action(ServiceType type, T content) {
    super();
    this.type = type;
    this.content = content;
  }

  public T getContent() {
    return content;
  }

  public ServiceType getType() {
    return type;
  }

}
