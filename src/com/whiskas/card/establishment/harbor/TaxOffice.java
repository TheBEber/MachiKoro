package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class TaxOffice extends Special {
  public TaxOffice() {
    diceNumberList = Arrays.asList(8, 9);
    buildingCost = 4;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentPlayer) {
    pickMoneyOnAllPlayers(game);
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    if (targetedPlayer.getMoney() >= 10) {
      return targetedPlayer.getMoney() / 2;
    }
    return 0;
  }
}
