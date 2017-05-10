package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public class TVStation extends Special {
  public TVStation() {
    diceNumberList = Arrays.asList(6);
    buildingCost = 7;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentPlayer) {
    Player targetedPlayer = Action.selectAPlayerById(game, this.getClass(), false, false);
    Action.transferMoneyFromPlayerToPlayer(targetedPlayer, currentPlayer, moneyToGet(game, currentPlayer), getClass());
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 5;
  }

}
