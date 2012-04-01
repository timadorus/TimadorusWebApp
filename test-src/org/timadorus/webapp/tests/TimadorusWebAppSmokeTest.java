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
import org.timadorus.webapp.tests.client.MenuDialogTest;
import org.timadorus.webapp.tests.client.SessionIdTest;
import org.timadorus.webapp.tests.server.CreateCharacterServiceTest;
import org.timadorus.webapp.tests.server.UtilTest;

/**
 * @author sage
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ // client
                      MenuDialogTest.class,
                      SessionIdTest.class,
                      // server
                      CreateCharacterServiceTest.class,
                      UtilTest.class
                   })
public class TimadorusWebAppSmokeTest {

}
