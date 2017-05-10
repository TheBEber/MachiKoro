package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Store;
import com.whiskas.card.landmark.basic.ShoppingMall;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public class FlowerShop extends Store {
  public FlowerShop() {
    diceNumberList = Arrays.asList(6);
    buildingCost = 1;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    Integer multiplier = 1;
    if (playerHasBuilt(targetedPlayer, ShoppingMall.class)) {
      multiplier = 2;
    }
    return targetedPlayer.getCards(FlowerOrchard.class).size() * multiplier;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    Action.transferMoneyFromBankToPlayer(targetedPlayer, moneyToGet(game, targetedPlayer), this.getClass());
  }
}
