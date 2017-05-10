package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Factory;
import com.whiskas.card.establishment.abs.Field;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class FruitAndVegetableMarket extends Factory {
  public FruitAndVegetableMarket() {
    diceNumberList = Arrays.asList(11, 12);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 2 * targetedPlayer.getCards(Field.class).size();
  }

}
