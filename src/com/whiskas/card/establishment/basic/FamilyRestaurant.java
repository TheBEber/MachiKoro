package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class FamilyRestaurant extends Restaurant {
  public FamilyRestaurant() {
    diceNumberList = Arrays.asList(9, 10);
    buildingCost = 3;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 2;
  }
}