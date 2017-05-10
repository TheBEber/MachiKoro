package com.whiskas.card.establishment.abs;

import com.whiskas.card.landmark.basic.ShoppingMall;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public abstract class Restaurant extends RedCard {
  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    Integer moneyToTransfert = moneyToGet(game, targetedPlayer);
    if (playerHasBuilt(targetedPlayer, ShoppingMall.class)) {
      moneyToTransfert = moneyToGet(game, targetedPlayer) + 1;
    }
    Action.transferMoneyFromPlayerToPlayer(game.getCurrentPlayer(), targetedPlayer, moneyToTransfert, getClass());
  }
}
