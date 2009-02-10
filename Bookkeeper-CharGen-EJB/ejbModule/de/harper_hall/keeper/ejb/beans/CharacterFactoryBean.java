package de.harper_hall.keeper.ejb.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.LocalBinding;
import org.jboss.ejb3.annotation.RemoteBinding;
import org.jboss.logging.Logger;

import de.harper_hall.keeper.character.CharCreator;
import de.harper_hall.keeper.character.entities.CharacterEntity;

/**
 * Session Bean implementation class CharacterFactoryBean
 */
@Stateless
@LocalBinding(jndiBinding = "BookKeeper/CharacterFactory/local")
@RemoteBinding(jndiBinding = "BookKeeper/CharacterFactory/remote")
public class CharacterFactoryBean implements CharacterFactoryRemote, CharacterFactory {
    
    @PersistenceContext(unitName = "keeper")
    EntityManager em;

    Logger logger = Logger.getLogger(CharacterFactoryBean.class);

    /**
     * Default constructor. 
     */
    public CharacterFactoryBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see de.harper_hall.keeper.ejb.interfaces.CharacterFactory#createCharacter()
     */
    public CharCreatorFacade createCharacter() {
      logger.debug("CharacterFactoryBean.createCharacter() called");

      if (em == null) {
        logger.error("createCharacter(): no EntityManager set!");
        return null;
      }

      CharacterEntity ce = new CharacterEntity();

      em.persist(ce);

      return new CharCreatorFacadeBean(new CharCreator(ce));
    }

    /**
     * 
     * @see de.harper_hall.keeper.ejb.interfaces.CharacterFactory#findCharacterById(java.lang.Long)
     */
    public KeeperCharacterFacade findCharacterById(Long id) {
      // TODO Auto-generated method stub
      return null;
    }

    /**
     * @see de.harper_hall.keeper.ejb.interfaces.RemoteCharacterFactory#ServerInfo()
     */
    public String serverInfo() {
      return "If you see this there probably exists a remote server";
    }


}
