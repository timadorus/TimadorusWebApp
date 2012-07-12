package org.timadorus.webapp.tests.server;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.timadorus.webapp.beans.CClass;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Faction;
import org.timadorus.webapp.beans.Race;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.client.service.ServiceAsync;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.server.rpc.service.CreateCharacterServiceImpl;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CharacterProviderTest {

  // noch fehlerhaft
  private final ServiceAsync<User, List<Character>> myService = GWT.create(Service.class);

  User user;

  CreateCharacterServiceImpl service;

  List<Character> charList;

  List<Character> expectedList = new ArrayList<Character>();

  @Before
  public void setUp() {
    System.out.println("HALLO WELT");
    System.err.println("TEST");
    user = new User("Vorname", "Nachname", "01.01.1970", "example@example.org", "Username", "password");
    // Chars erstellen
    service = new CreateCharacterServiceImpl(null);

    Character character = new Character();

    Faction faction = new Faction();
    faction.setName("Gnome von Zürich");
    character.setFaction(faction);

    Race race = new Race();
    race.setName("Gnom");
    character.setRace(race);

    CClass charClass = new CClass(null, null);
    charClass.setName("Bankier");
    character.setCharClass(charClass);

    String usernameIn = "Username";
    character.setUsername(usernameIn);

    expectedList.add(character);

    service.createCharacter(character);
  }

  @Test
  public void test() {
    Action<User> action = new Action<User>(ServiceType.GETCHARACTERS, user);

    AsyncCallback<Response<List<Character>>> response = new AsyncCallback<Response<List<Character>>>() {

      @Override
      public void onFailure(Throwable arg0) {
        System.out.println("onFailure");
        // Bleibt leer, weil wir hier richtiges erwarten und keinen Fehler produzieren wollen

      }

      public void onSuccess(Response<List<Character>> result) {
        System.out.println("onSuccess");
        charList = result.getResult();
      }
    };
    myService.execute(action, response);
    // Liste

    System.err.println("expectedList");
    System.err.println(expectedList);
    System.err.println("charList");
    System.err.println(charList);
    Assert.assertEquals(expectedList, charList);
  }
}
