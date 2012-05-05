package org.timadorus.webapp.client.character.ui.selecttempstats;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;

public class SelectTempStatsDialog extends DefaultDialog<SelectTempStatsDialog.Display> {

  public interface Display extends DefaultDisplay {
    public void addNextButtonHandler(DefaultActionHandler handler);

    public void addPrevButtonHandler(DefaultActionHandler handler);

    public void addIncreaseStatByOneHandler(StatsChangedHandler handler);

    public void addIncreaseStatByTenHandler(StatsChangedHandler handler);

    public void addDecreaseStatByOneHandler(StatsChangedHandler handler);

    public void addDecreaseStatByTenHandler(StatsChangedHandler handler);

    public void doStatsChanged(int index, int value, int statPoints, int statCosts);

    public void setStatCostColorRed(int index);

    public void setStatCostColorGreen(int index);

    public void loadSelectFactionPanel(DefaultTimadorusWebApp entry, Character character, User user);

    public void loadGetPotStatsPanel(DefaultTimadorusWebApp entry, Character character, User user);
  }

  private User user;

  private Character character;

  // list for handling tempstats
  private List<Integer> tempStats;

  private int statPoints;

  public int getStatPoints() {
    return statPoints;
  }

  public SelectTempStatsDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user,
                               List<Integer> tempStats, int statPoints) {
    super(display, entry);
    tempStats = new ArrayList<Integer>();
    this.statPoints = statPoints;

    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onNextButtonClick();
      }
    });
    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onPrevButtonClick();
      }
    });
    display.addDecreaseStatByOneHandler(new StatsChangedHandler() {

      @Override
      public void onAction(int ammountOfStats) {
        for (int i = 0; i < ammountOfStats; i++) {
          decStat(i);
          getDisplay().doStatsChanged(i, getTempStats().get(i), getStatPoints(),
                                      getStatCosts(getTempStats().get(i) + 1));
        }
        onStatsChanged(ammountOfStats);
      }
    });
    display.addDecreaseStatByTenHandler(new StatsChangedHandler() {

      @Override
      public void onAction(int ammountOfStats) {
        for (int i = 0; i < ammountOfStats; i++) {
          for (int y = 0; y < 10; y++) {
            decStat(i);
          }
          getDisplay().doStatsChanged(i, getTempStats().get(i), getStatPoints(),
                                      getStatCosts(getTempStats().get(i) + 1));
        }
        onStatsChanged(ammountOfStats);
      }
    });
    display.addIncreaseStatByOneHandler(new StatsChangedHandler() {

      @Override
      public void onAction(int ammountOfStats) {
        for (int i = 0; i < ammountOfStats; i++) {
          incStat(i);
          getDisplay().doStatsChanged(i, getTempStats().get(i), getStatPoints(),
                                      getStatCosts(getTempStats().get(i) + 1));
        }
        onStatsChanged(ammountOfStats);
      }
    });
    display.addIncreaseStatByTenHandler(new StatsChangedHandler() {

      @Override
      public void onAction(int ammountOfStats) {
        for (int i = 0; i < ammountOfStats; i++) {
          for (int y = 0; y < 10; y++) {
            incStat(i);
          }
          getDisplay().doStatsChanged(i, getTempStats().get(i), getStatPoints(),
                                      getStatCosts(getTempStats().get(i) + 1));
        }
        onStatsChanged(ammountOfStats);
      }
    });
  }

  protected void onStatsChanged(int ammountOfStats) {
    for (int i = 0; i < ammountOfStats; i++) {
      int nextCost = getStatCosts(tempStats.get(i) + 1);
      if ((nextCost > statPoints) || ((tempStats.get(i)) == 100)) {
        getDisplay().setStatCostColorRed(i);
      } else {
        getDisplay().setStatCostColorGreen(i);
      }
    }
  }

  private void onPrevButtonClick() {
    loadSelectFactionPanel();
  }

  private void onNextButtonClick() {
    saveTempStats();
    loadGetPotStatsPanel();
  }

  // clear "content" #div and add Class SelectFactionPanel to it
  public void loadSelectFactionPanel() {
    getDisplay().loadSelectFactionPanel(getEntry(), getCharacter(), getUser());
  }

  // clear "content" #div and add Class GetPotStatsPanel to it
  public void loadGetPotStatsPanel() {
    getDisplay().loadGetPotStatsPanel(getEntry(), getCharacter(), getUser());
  }

  /**
   * saving tempstats
   */
  public void saveTempStats() {
    if (!tempStats.isEmpty()) {
      character.setTempStat(tempStats);
    } else {
      System.out.println("tempStats is empty");
    }
  }

  public User getUser() {
    return user;
  }

  public Character getCharacter() {
    return character;
  }

  /**
   * calculates current statcost for stat i
   * 
   * @param currentStat
   * @return
   */
  public int getStatCosts(int currentStat) {
    return (currentStat > 90) ? 10 : 1;
  }

  /**
   * increases the stat i
   * 
   * @param i
   */
  public void incStat(int i) {
    if (((tempStats.get(i)) < 100) && (statPoints >= (getStatCosts(tempStats.get(i) + 1)))) {
      decStatPoints(getStatCosts(tempStats.get(i) + 1));
      tempStats.set(i, (tempStats.get(i) + 1));
    } else {
      System.out.print("maximal 100");
    }
  }

  /**
   * decreases the stat i
   * 
   * @param i
   */
  public void decStat(int i) {
    if (((tempStats.get(i)) > 30) && (statPoints < 750)) {
      incStatPoints(getStatCosts(tempStats.get(i)));
      tempStats.set(i, (tempStats.get(i) - 1));
    } else {
      System.out.print("minimal 30");
    }
  }

  // increase available stat points
  public void incStatPoints(int costs) {
    if (statPoints < 420) {
      statPoints = statPoints + costs;
    } else {
      System.out.print("maximal 420");
    }
  }

  // decreases available stat points
  public void decStatPoints(int costs) {
    if (statPoints > 0) {
      statPoints = statPoints - costs;
    } else {
      System.out.print("minimal 0");
    }
  }

  public static SelectTempStatsDialog getDialog(DefaultTimadorusWebApp entry, User user, Character character) {
    int statPoints = 420;
    List<Integer> tempStats = new ArrayList<Integer>();
    for (int i = 0; i < 12; i++) {
      tempStats.add(character.getStatList().get(i).getTempStat());
    }
    SelectTempStatsDialog.Display display = new SelectTempStatsWidget(character, tempStats, statPoints);
    SelectTempStatsDialog dialog = new SelectTempStatsDialog(display, entry, character, user, tempStats, statPoints);
    return dialog;
  }

  public List<Integer> getTempStats() {
    return tempStats;
  }
}
