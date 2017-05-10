package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Factory;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class CheeseFactory extends Factory {
  public CheeseFactory() {
    diceNumberList = Arrays.asList(7);
    buildingCost = 5;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 3 * targetedPlayer.getCards(Ranch.class).size();
  }
}
