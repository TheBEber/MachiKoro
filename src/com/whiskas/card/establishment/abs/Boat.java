package com.whiskas.card.establishment.abs;

import com.whiskas.card.landmark.harbor.Harbour;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public abstract class Boat extends BlueCard {
  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    if (playerHasBuilt(targetedPlayer, Harbour.class)) {
      Action.transferMoneyFromBankToPlayer(targetedPlayer, moneyToGet(game, targetedPlayer), this.getClass());
    }
  }
}
