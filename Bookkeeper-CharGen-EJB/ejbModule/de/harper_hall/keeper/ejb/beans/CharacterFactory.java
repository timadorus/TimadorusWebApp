package de.harper_hall.keeper.ejb.beans;
import javax.ejb.Local;

@Local
public interface CharacterFactory {
  
  /** create a new character object
   * 
   * @return the facade to a character object, newly created by the backing EJB
   */
  public CharCreatorFacade createCharacter();
  
  /** find character object by id
   * 
   * @param id
   * @return
   */
  public KeeperCharacterFacade findCharacterById(Long id);
  
}
