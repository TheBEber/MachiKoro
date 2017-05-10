package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Field;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class AppleOrchard extends Field {
  public AppleOrchard() {
    diceNumberList = Arrays.asList(10);
    buildingCost = 3;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 3;
  }

}
