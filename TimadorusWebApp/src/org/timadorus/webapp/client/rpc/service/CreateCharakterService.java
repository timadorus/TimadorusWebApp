package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.Charakter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface CreateCharakterService extends RemoteService {
	String createCharakter(Charakter name);
}
