/* -*- java -*- */
/*
 * Filename:          org.timadorus.webapp.tests/TimadorusWebAppSmokeTest.java
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

package org.timadorus.webapp.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.timadorus.webapp.tests.client.HistoryStatesTest;
import org.timadorus.webapp.tests.client.MenuDialogTest;
import org.timadorus.webapp.tests.client.RoleTest;
import org.timadorus.webapp.tests.client.SessionIdTest;
import org.timadorus.webapp.tests.client.character.ui.createcharacter.CreateDialogTest;
import org.timadorus.webapp.tests.client.character.ui.potstat.PotStatsDialogTest;
import org.timadorus.webapp.tests.client.character.ui.premadecharacter.PremadeCharacterDialogTest;
import org.timadorus.webapp.tests.client.character.ui.ready.ReadyDialogTest;
import org.timadorus.webapp.tests.client.character.ui.selectappearance.SelectAppearanceDialogTest;
import org.timadorus.webapp.tests.client.character.ui.selectclass.SelectClassDialogTest;
import org.timadorus.webapp.tests.client.character.ui.selectfraction.SelectFactionDialogTest;
import org.timadorus.webapp.tests.client.character.ui.selectname.SelectNameDialogTest;
import org.timadorus.webapp.tests.client.character.ui.selectrace.SelectRaceDialogTest;
import org.timadorus.webapp.tests.client.character.ui.selectskilllevel.SelectSkillLevelDialogTest;
import org.timadorus.webapp.tests.server.CreateCharacterServiceTest;
import org.timadorus.webapp.tests.server.RegisteredUserListTest;
import org.timadorus.webapp.tests.server.UtilTest;
import org.timadorus.webapp.tests.shared.ActionTest;
import org.timadorus.webapp.tests.shared.ResponseTest;
import org.timadorus.webapp.tests.util.ListUtilTest;

/**
 * @author sage
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
                     // character ui
                     CreateDialogTest.class, 
                     PotStatsDialogTest.class, 
                     PremadeCharacterDialogTest.class,
                     ReadyDialogTest.class, 
                     SelectAppearanceDialogTest.class, 
                     SelectClassDialogTest.class,
                     SelectFactionDialogTest.class, 
                     SelectNameDialogTest.class, 
                     SelectRaceDialogTest.class,
                     SelectSkillLevelDialogTest.class,

                     // client
                     HistoryStatesTest.class, 
                     MenuDialogTest.class, 
                     RoleTest.class, 
                     SessionIdTest.class,

                     // server
                     // CharacterProviderTest.class,
                     CreateCharacterServiceTest.class, 
                     RegisteredUserListTest.class,
                     // UserProviderTest.class,
                     UtilTest.class,
                     
                     // shared
                     ActionTest.class,
                     ResponseTest.class,
                     
                     // util
                     ListUtilTest.class
})
public class TimadorusWebAppSmokeTest {

}
