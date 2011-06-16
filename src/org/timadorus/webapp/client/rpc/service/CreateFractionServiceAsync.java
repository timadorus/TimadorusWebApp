package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.campaign.Fraction;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CreateFractionServiceAsync {

  void createFraction(Fraction fraction, AsyncCallback<String> asyncCallback);

}
