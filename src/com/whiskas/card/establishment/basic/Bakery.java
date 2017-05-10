package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Store;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Bakery extends Store {
  public Bakery() {
    diceNumberList = Arrays.asList(2, 3);
    buildingCost = 1;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }
}
