package com.whiskas.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.card.landmark.abs.Landmark;
import com.whiskas.data.Board;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Display {

  public static void text(String text) {
    System.out.println(text);
  }

  public static void alertNoMoreGold(Integer playerId) {
    Display.text(getPlayerName(playerId) + " have no more gold to give !");
  }

  public static String getPlayerName(Integer playerId) {
    return "Player " + (playerId + 1);
  }

  public static void goldTransfer(Integer playerId, Integer moneyWon, String from) {
    Display.text("Player " + (playerId + 1) + " received " + moneyWon + " from " + from);
  }

  public static List<Landmark> landmarks(Player targetedPlayer, boolean onlyBuilt) {
    List<Landmark> listOfLandmarks = PlayerUtils.getLandmarks(targetedPlayer, true, onlyBuilt);

    for (int i = 0; i < listOfLandmarks.size(); i++) {
      Display.text(i + "\t" + listOfLandmarks.get(i).getBuildingCost() + "\t" +
          listOfLandmarks.get(i).getClass().getSimpleName());
    }
    return listOfLandmarks;
  }

  public static List<ArrayList<Establishment>> listOfEtablishementFromPlayer(Player targetedplayer,
      boolean avoidSpecial, boolean avoidRestaurant) {
    List<Establishment> listOfEstablishment = targetedplayer.getCards(Establishment.class, avoidSpecial,
        avoidRestaurant);
    List<ArrayList<Establishment>> sortedList = PlayerUtils.sortPlayerCard(listOfEstablishment);
    for (int i = 0; i < sortedList.size(); i++) {
      Display.text(i + "\t" + sortedList.get(i).get(0).getDiceString() + "\t" + sortedList.get(i).size() + " " +
          sortedList.get(i).get(0).getClass().getSimpleName());
    }
    return sortedList;
  }

  public static List<ArrayList<Establishment>> allEstablishementOnTableSorted(GameContext game) {
    List<ArrayList<Establishment>> mergedSortedList = PlayerUtils.sortPlayerCard(game.getCurrentPlayer().getCards(
        Establishment.class, true, false));
    Integer idplayer = (game.getCurrentPlayer().getId() + game.getListOfPlayers().size() - 1) %
        game.getListOfPlayers().size();
    for (int i = game.getListOfPlayers().size() - 1; i > 0; i--) {
      mergedSortedList = merge(mergedSortedList,
          PlayerUtils.sortPlayerCard(game.getListOfPlayers().get(idplayer).getCards(Establishment.class, true, false)));
      idplayer = (idplayer + game.getListOfPlayers().size() - 1) % game.getListOfPlayers().size();
    }
    for (int j = 0; j < mergedSortedList.size(); j++) {
      Display.text(j + "\t" + mergedSortedList.get(j).get(0).getDiceString() + "\t" +
          mergedSortedList.get(j).size() + " " +
          mergedSortedList.get(j).get(0).getClass().getSimpleName());
    }
    return mergedSortedList;
  }

  private static List<ArrayList<Establishment>> merge(List<ArrayList<Establishment>> mergedSortedList,
      List<ArrayList<Establishment>> sortedListToAdd) {
    for (int i = 0; i < sortedListToAdd.size(); i++) {
      boolean alreadyHere = false;
      ArrayList<Establishment> currentListOfEstablishmentToAdd = sortedListToAdd.get(i);
      for (int j = 0; j < mergedSortedList.size(); j++) {
        if (mergedSortedList.get(j).get(0).getClass().isInstance(currentListOfEstablishmentToAdd.get(0))) {
          mergedSortedList.get(j).addAll(currentListOfEstablishmentToAdd);
          alreadyHere = true;

        }
      }
      if (!alreadyHere) {
        mergedSortedList.add(sortedListToAdd.get(i));
      }
    }
    Collections.sort(mergedSortedList, new Comparator<ArrayList<Establishment>>() {
      @Override
      public int compare(ArrayList<Establishment> list1, ArrayList<Establishment> list2) {
        return list1.get(0).getDiceNumberList().get(0) - list2.get(0).getDiceNumberList().get(0);
      }
    });
    return mergedSortedList;
  }

  public static void newTurn(GameContext game) {
    Display.text("");
    Display.text("-------------------------------------------------------------------");
    Display.text("TURN OF PLAYER : " + (game.getCurrentPlayer().getId() + 1));
  }

  public static void allPlayersStatus(GameContext game) {
    List<String> stringList = new ArrayList<String>();
    for (int s = 0; s < 24; s++) {
      stringList.add("");
    }

    List<ArrayList<Landmark>> listOfLandmarkList = new ArrayList<ArrayList<Landmark>>();
    List<ArrayList<ArrayList<Establishment>>> listOfSortedListOfEtablissements = new ArrayList<ArrayList<ArrayList<Establishment>>>();
    for (int e = 0; e < game.getListOfPlayers().size(); e++) {
      listOfLandmarkList
          .add((ArrayList<Landmark>)PlayerUtils.getLandmarks(game.getListOfPlayers().get(e), true, false));
      listOfSortedListOfEtablissements.add((ArrayList<ArrayList<Establishment>>)PlayerUtils.sortPlayerCard(game
          .getListOfPlayers().get(e)
          .getCards(Establishment.class)));
    }

    // 1srt row
    Display.text("");
    for (int f = 0; f < game.getListOfPlayers().size(); f++) {
      stringList.set(4 * f, game.getListOfPlayers().size() > f ? "Player " + (f + 1) : "");
      stringList.set(4 * f + 2, game.getListOfPlayers().size() >= f ? "(Gold = " +
          game.getListOfPlayers().get(f).getMoney() + ")" : "");
    }
    Display.formatPlayers(stringList);

    // Landmarks
    for (int l = 0; l < listOfLandmarkList.get(0).size(); l++) {
      reset(stringList);
      for (int f = 0; f < game.getListOfPlayers().size(); f++) {
        stringList.set(4 * f + 1, listOfLandmarkList.get(f).get(l).isBuilt() ? "O" : "X");
        stringList.set(4 * f + 2, listOfLandmarkList.get(f).get(l).getClass().getSimpleName());

      }
      Display.formatPlayers(stringList);
    }

    // Establishement
    Integer establishementDisplayMax = 0;
    for (int i = 0; i < listOfSortedListOfEtablissements.size(); i++) {
      establishementDisplayMax = Math.max(establishementDisplayMax, listOfSortedListOfEtablissements.get(i).size());
    }
    for (int m = 0; m < establishementDisplayMax; m++) {
      reset(stringList);
      for (int f = 0; f < game.getListOfPlayers().size(); f++) {
        if (listOfSortedListOfEtablissements.get(f).size() > m) {
          stringList.set(4 * f,
              listOfSortedListOfEtablissements.get(f).get(m) != null ? listOfSortedListOfEtablissements
                  .get(f).get(m).get(0).getDiceString() : "");
          stringList.set(4 * f + 1,
              listOfSortedListOfEtablissements.get(f).get(m) != null ? String.valueOf(listOfSortedListOfEtablissements
                  .get(f).get(m).size()) : "");
          stringList.set(4 * f + 2,
              listOfSortedListOfEtablissements.get(f).get(m) != null ? listOfSortedListOfEtablissements.get(f).get(m)
                  .get(0).getClass().getSimpleName() : "");
        }
      }
      Display.formatPlayers(stringList);
    }
    Display.text("");

  }

  public static void board(Board board) {
    List<String> stringList = new ArrayList<String>();
    for (int s = 0; s < 24; s++) {
      stringList.add("");
    }
    stringList.set(0, "Slot");
    stringList.set(1, "Name");
    stringList.set(2, "Qty");
    stringList.set(3, "Cost");
    stringList.set(4, "Dice");
    formatBoard(stringList);
    for (int i = 0; i < board.maxBoardSlot(); i++) {
      reset(stringList);
      if (board.get(i).isEmpty()) {
        stringList.set(0, String.valueOf(i));
        stringList.set(1, "Slot is Empty !");
      }
      else {
        stringList.set(0, String.valueOf(i));
        stringList.set(1, board.get(i).get(0).getClass().getSimpleName());
        stringList.set(2, String.valueOf(board.get(i).size()));
        stringList.set(3, String.valueOf(board.get(i).get(0).getBuildingCost()));
        stringList.set(4, board.get(i).get(0).getDiceString());
      }
      formatBoard(stringList);
    }
    Display.text("");
  }

  private static void reset(List<String> stringList) {
    for (int s = 0; s < 24; s++) {
      stringList.set(s, "");
    }
  }

  private static void formatPlayers(List<String> stringList) {
    String format = "%-8s %-1s %-25s %-1s %-8s %-1s %-25s %-1s %-8s %-1s %-25s %-1s %-8s %-1s %-25s %-1s %-8s %-1s %-25s %-1s %-8s %-1s %-25s %-1s\n";
    System.out.printf(format, stringList.get(0), stringList.get(1), stringList.get(2), stringList.get(3),
        stringList.get(4), stringList.get(5), stringList.get(6), stringList.get(7), stringList.get(8),
        stringList.get(9), stringList.get(10), stringList.get(11), stringList.get(12), stringList.get(13),
        stringList.get(14), stringList.get(15), stringList.get(16), stringList.get(17), stringList.get(18),
        stringList.get(19), stringList.get(20), stringList.get(21), stringList.get(22), stringList.get(23));
  }

  private static void formatBoard(List<String> stringList) {
    String format = "%-4s %-25s %-3s %-4s %-8s\n";
    System.out.printf(format, stringList.get(0), stringList.get(1), stringList.get(2), stringList.get(3),
        stringList.get(4));
  }
}
