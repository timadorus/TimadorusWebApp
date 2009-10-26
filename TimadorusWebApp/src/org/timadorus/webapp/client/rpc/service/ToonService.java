package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface ToonService extends RemoteService {
	Character createCharacter(String name);
}
