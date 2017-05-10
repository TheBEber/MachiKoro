package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Boat;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class TunaBoat extends Boat {
  public TunaBoat() {
    diceNumberList = Arrays.asList(12, 13, 14);
    buildingCost = 5;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return game.getTunaBoatRollResult();
  }

}
