package com.whiskas.card.establishment.promo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Display;
import com.whiskas.utils.Input;

public class InternationalExhibitHall extends Special {

  public InternationalExhibitHall() {
    diceNumberList = Arrays.asList(10);
    buildingCost = 7;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return null;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    while (true) {
      Establishment cardToTrigger = null;
      List<ArrayList<Establishment>> sortedList = Display.listOfEtablishementFromPlayer(game.getCurrentPlayer(), false,
          true);
      Integer cardID = Input.getInteger(" cardID to trigger (from " + this.getClass().getSimpleName() + ")");
      if (cardID >= 0 && cardID < sortedList.size()) {
        for (int i = 0; i < sortedList.get(cardID).size(); i++) {
          cardToTrigger = sortedList.get(cardID).get(i);
          cardToTrigger.applyEffect(game, game.getCurrentPlayer());
        }
        close();
        return;
      }
    }
  }
}
