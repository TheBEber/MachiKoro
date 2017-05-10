package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Production;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Mine extends Production {
  public Mine() {
    diceNumberList = Arrays.asList(9);
    buildingCost = 6;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 5;
  }

}
