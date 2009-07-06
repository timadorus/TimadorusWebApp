package org.timadorus.webapp.server.rpc.service;

import org.timadorus.webapp.client.Charakter;
import org.timadorus.webapp.client.rpc.service.CreateCharakterService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CreateCharakterServiceImpl extends RemoteServiceServlet implements CreateCharakterService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String createCharakter(Charakter name) {
		return name.getName();
	}
}

