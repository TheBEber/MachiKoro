package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public class TechStartup extends Special {
  protected Integer counter = 0;

  public TechStartup() {
    diceNumberList = Arrays.asList(10);
    buildingCost = 1;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    Integer totalCounterOnBoard = 0;
    for (int i = 0; i < game.getListOfPlayers().size(); i++) {
      if (game.getListOfPlayers().get(i).getCard(TechStartup.class) != null) {
        totalCounterOnBoard += game.getListOfPlayers().get(i).getCard(TechStartup.class).getStartupCounter();
      }
    }

    return totalCounterOnBoard;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    Action.transferMoneyFromBankToPlayer(targetedPlayer, moneyToGet(game, targetedPlayer), this.getClass());
    counter++;
  }

  public Integer getStartupCounter() {
    return counter;
  }
}
