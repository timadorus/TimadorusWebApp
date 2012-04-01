package org.timadorus.webapp.shared;

import java.io.Serializable;

public class Response<T> implements Serializable {

  private static final long serialVersionUID = -8937715744084440933L;

  private T result;

  public Response() {
  }

  public Response(T result) {
    super();
    this.result = result;
  }

  public T getResult() {
    return result;
  }
}
