package com.whiskas.card.establishment.abs;

import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public abstract class Factory extends GreenCard {
  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    Action.transferMoneyFromBankToPlayer(targetedPlayer, moneyToGet(game, targetedPlayer), getClass());
  }
}
