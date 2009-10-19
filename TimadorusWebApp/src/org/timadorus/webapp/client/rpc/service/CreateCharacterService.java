package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.Charakter;

import com.google.appengine.repackaged.com.google.common.base.Flag.String;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface CreateCharacterService  extends RemoteService{
	Charakter createCharacter(String name);

	//char createCharacter(java.lang.String input);
}
