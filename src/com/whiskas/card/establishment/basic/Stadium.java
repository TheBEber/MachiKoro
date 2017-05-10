package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Stadium extends Special {
  public Stadium() {
    diceNumberList = Arrays.asList(6);
    buildingCost = 6;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentPlayer) {
    pickMoneyOnAllPlayers(game);
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 2;
  }
}
