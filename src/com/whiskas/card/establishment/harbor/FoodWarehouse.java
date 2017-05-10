package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Factory;
import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class FoodWarehouse extends Factory {
  public FoodWarehouse() {
    diceNumberList = Arrays.asList(12, 13);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 2 * targetedPlayer.getCards(Restaurant.class).size();
  }

}
