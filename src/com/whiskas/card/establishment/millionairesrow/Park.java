package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Park extends Special {
  public Park() {
    diceNumberList = Arrays.asList(11, 12, 13);
    buildingCost = 3;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return null;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    Integer totalAmountOnBoard = 0;
    for (int i = 0; i < game.getListOfPlayers().size(); i++) {
      totalAmountOnBoard += game.getListOfPlayers().get(i).getMoney();
    }
    totalAmountOnBoard += totalAmountOnBoard % game.getListOfPlayers().size();
    for (int i = 0; i < game.getListOfPlayers().size(); i++) {
      game.getListOfPlayers().get(i).setMoney(totalAmountOnBoard / game.getListOfPlayers().size());
    }
  }
}
