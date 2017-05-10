package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Production;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Forest extends Production {
  public Forest() {
    diceNumberList = Arrays.asList(5);
    buildingCost = 3;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }

}
