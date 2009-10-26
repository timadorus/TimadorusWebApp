package org.timadorus.webapp.server.rpc.service;

import org.timadorus.webapp.client.Toon;
import org.timadorus.webapp.client.rpc.service.ToonService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToonServiceImpl extends RemoteServiceServlet implements ToonService{

	/**
	 * character ff charakter 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Character createCharacter(String name) {
		return null;
	}
}

