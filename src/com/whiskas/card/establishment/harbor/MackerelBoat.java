package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Boat;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class MackerelBoat extends Boat {
  public MackerelBoat() {
    diceNumberList = Arrays.asList(8);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 3;
  }

}
