package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Factory;
import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class SodaBottlingPlant extends Factory {
  public SodaBottlingPlant() {
    diceNumberList = Arrays.asList(11);
    buildingCost = 5;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    Integer amount = 0;
    for (int i = 0; i < game.getListOfPlayers().size(); i++) {
      amount += game.getListOfPlayers().get(i).getCards(Restaurant.class).size();
    }
    return amount;
  }
}
