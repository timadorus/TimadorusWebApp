package de.harper_hall.keeper.ejb.beans;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class KeeperCharacterFacadeBean
 */
@Stateless
public class KeeperCharacterFacadeBean implements KeeperCharacterFacadeRemote, KeeperCharacterFacade {

    /**
     * Default constructor. 
     */
    public KeeperCharacterFacadeBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @see de.harper_hall.keeper.ejb.interfaces.RemoteKeeperCharacterFacade#ServerInfo()
     */
    public String serverInfo() {
      // TODO Auto-generated method stub
      return null;
    }

}
