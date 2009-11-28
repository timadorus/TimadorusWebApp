package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Class implements Serializable {

  String name;

  String description;

  private static final long serialVersionUID = 1L;

  public Class() {

  }

  public Class(String name, String description) {

    this.name = name;
    this.description = description;

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static List<Class> getSampleClasses() {

    Class c1 = new Class(
                         "Capitalist class",
                         "Top-level executives, high-rung politicians, heirs. Ivy League education common. Source: http://en.wikipedia.org/wiki/Social_class");

    Class c2 = new Class(
                         "Upper middle class",
                         "Highly educated (often with graduate degrees), most commonly salaried, professionals and middle management with large work autonomy. Source: http://en.wikipedia.org/wiki/Social_class");

    Class c3 = new Class(
                         "Working class",
                         "Clerical and most blue collar workers whose work is highly routinized. Standard of living varies depending on number of income earners, but is commonly just adequate. High school education. Source: http://en.wikipedia.org/wiki/Social_class");

    Class c4 = new Class(
                         "Underclass",
                         "Those with limited or no participation in the labor force. Reliant on government transfers. Some high school education. Source: http://en.wikipedia.org/wiki/Social_class");

    ArrayList<Class> clist = new ArrayList<Class>();
    clist.add(c1);
    clist.add(c2);
    clist.add(c3);
    clist.add(c4);

    return clist;
  }

}
