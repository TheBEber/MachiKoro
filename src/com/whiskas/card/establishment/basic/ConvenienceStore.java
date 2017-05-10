package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Store;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class ConvenienceStore extends Store {
  public ConvenienceStore() {
    diceNumberList = Arrays.asList(4);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 3;
  }
}
