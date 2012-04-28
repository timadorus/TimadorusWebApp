package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.Fraction;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("createFraction")
public interface CreateFractionService extends RemoteService {

  String createFraction(Fraction fraction);

  String existsFraction(String fractionName, String campaignName);

}
