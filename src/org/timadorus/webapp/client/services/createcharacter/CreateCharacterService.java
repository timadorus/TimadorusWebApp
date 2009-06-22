package org.timadorus.webapp.client.services.createcharacter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface CreateCharacterService extends RemoteService {
	String createCharacter(String name);
}
