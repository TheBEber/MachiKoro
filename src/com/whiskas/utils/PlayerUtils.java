package com.whiskas.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.card.landmark.abs.Landmark;
import com.whiskas.data.Player;

public class PlayerUtils {
  public static List<ArrayList<Establishment>> sortPlayerCard(List<Establishment> listOfEstablishment) {
    List<ArrayList<Establishment>> sortedList = new ArrayList<ArrayList<Establishment>>();
    for (int i = 0; i < listOfEstablishment.size(); i++) {
      boolean added = false;
      for (int j = 0; j < sortedList.size(); j++) {
        if (listOfEstablishment.get(i).getClass().isInstance(sortedList.get(j).get(0))) {
          sortedList.get(j).add(listOfEstablishment.get(i));
          added = true;
        }
      }
      if (!added) {
        sortedList.add(new ArrayList<Establishment>(Arrays.asList(listOfEstablishment.get(i))));
      }
    }
    Collections.sort(sortedList, new Comparator<ArrayList<Establishment>>() {
      @Override
      public int compare(ArrayList<Establishment> list1, ArrayList<Establishment> list2) {
        return list1.get(0).getDiceNumberList().get(0) - list2.get(0).getDiceNumberList().get(0);
      }
    });

    return sortedList;
  }

  public static List<Landmark> getLandmarks(Player targetedPlayer, boolean onlyConstructible, boolean onlyBuilt) {
    List<Landmark> listOfLandmarks = targetedPlayer.getCards(Landmark.class);
    List<Landmark> listOfBuiltLandmarks = new ArrayList<Landmark>();
    if (onlyConstructible) {
      for (int i = 0; i < listOfLandmarks.size(); i++) {
        if (listOfLandmarks.get(i).isContructible()) {
          if (onlyBuilt) {
            if (listOfLandmarks.get(i).isBuilt()) {
              listOfBuiltLandmarks.add(listOfLandmarks.get(i));
            }
          }
          else {
            listOfBuiltLandmarks.add(listOfLandmarks.get(i));
          }
        }
      }
      listOfLandmarks = listOfBuiltLandmarks;
    }
    return listOfBuiltLandmarks;

  }
}
