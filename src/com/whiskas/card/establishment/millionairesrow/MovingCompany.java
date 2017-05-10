package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Company;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public class MovingCompany extends Company {
  public MovingCompany() {
    diceNumberList = Arrays.asList(9, 10);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 4;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentPlayer) {
    while (true) {
      Player targetedPlayer = Action.selectAPlayerById(game, this.getClass(), false, false);
      if (targetedPlayer != null &&
          Action.transferEstablishmentFromPlayerToPlayer(currentPlayer, targetedPlayer) != null) {
        Action.transferMoneyFromBankToPlayer(currentPlayer, moneyToGet(game, currentPlayer), this.getClass());
        return;
      }
    }
  }
}
