package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public class BusinessCenter extends Special {
  public BusinessCenter() {
    diceNumberList = Arrays.asList(6);
    buildingCost = 7;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentPlayer) {
    while (true) {
      Player targetedPlayer = Action.selectAPlayerById(game, this.getClass(), false, true);
      if (targetedPlayer != null &&
          Action.transferEstablishmentFromPlayerToPlayer(targetedPlayer, currentPlayer) != null) {
        while (true) {
          if (Action.transferEstablishmentFromPlayerToPlayer(currentPlayer, targetedPlayer) != null) {
            return;
          }
        }
      }
      return;
    }
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return null;
  }
}
