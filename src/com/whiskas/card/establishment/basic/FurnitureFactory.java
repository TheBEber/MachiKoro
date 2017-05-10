package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Factory;
import com.whiskas.card.establishment.abs.Production;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class FurnitureFactory extends Factory {
  public FurnitureFactory() {
    diceNumberList = Arrays.asList(8);
    buildingCost = 3;
  }


  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 3 * targetedPlayer.getCards(Production.class).size();
  }

}
