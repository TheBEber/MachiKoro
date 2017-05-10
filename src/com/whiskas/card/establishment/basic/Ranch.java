package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Farm;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Ranch extends Farm {
  public Ranch() {
    diceNumberList = Arrays.asList(2);
    buildingCost = 1;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }

}
