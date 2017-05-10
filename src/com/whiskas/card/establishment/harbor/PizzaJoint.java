package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class PizzaJoint extends Restaurant {
  public PizzaJoint() {
    diceNumberList = Arrays.asList(7);
    buildingCost = 1;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }
}
