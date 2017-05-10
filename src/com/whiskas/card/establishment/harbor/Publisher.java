package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.card.establishment.abs.Special;
import com.whiskas.card.establishment.abs.Store;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Publisher extends Special {
  public Publisher() {
    diceNumberList = Arrays.asList(7);
    buildingCost = 5;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentPlayer) {
    pickMoneyOnAllPlayers(game);
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return targetedPlayer.getCards(Restaurant.class).size() + targetedPlayer.getCards(Store.class).size();
  }
}
