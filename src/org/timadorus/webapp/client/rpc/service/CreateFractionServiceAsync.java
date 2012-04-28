package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.Fraction;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CreateFractionServiceAsync {

  void createFraction(Fraction fraction, AsyncCallback<String> asyncCallback);

  void existsFraction(String fractionName, String campaignName, AsyncCallback<String> asyncCallback);

}
