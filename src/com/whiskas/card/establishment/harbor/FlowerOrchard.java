package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Field;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class FlowerOrchard extends Field {
  public FlowerOrchard() {
    diceNumberList = Arrays.asList(4);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }

}
