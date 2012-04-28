/* -*- java -*- */
/*
 * Filename:          org.timadorus.webapp.tests.server/CreateCharacterServiceTest.java
 *                                                                       *
 * Project:           TimadorusWebApp
 *
 * This file is distributed under the GNU Public License 2.0
 * See the file Copying for more information
 *
 * copyright (c) 2012 Lutz Behnke <lutz.behnke@gmx.de>
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package org.timadorus.webapp.tests.server;

import org.junit.Test;
import org.timadorus.webapp.beans.CClass;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Faction;
import org.timadorus.webapp.beans.Race;
import org.timadorus.webapp.server.rpc.service.CreateCharacterServiceImpl;

/**
 * @author sage
 *
 */
public class CreateCharacterServiceTest {

  /**
   * 
   * @throws Exception for any exception not caught by test
   */
  @Test
  public void testCreateCharacter() {
    CreateCharacterServiceImpl service = new CreateCharacterServiceImpl();
    
    Character character = new Character();
    
    Faction faction = new Faction();
    faction.setName("Gnome von Zürich");    
    character.setFaction(faction);
    
    Race race = new Race();
    race.setName("Gnom");
    character.setRace(race);
    
    CClass charClass = new CClass();
    charClass.setName("Bankier");    
    character.setCharClass(charClass);
    
    service.createCharacter(character);
  }
}
