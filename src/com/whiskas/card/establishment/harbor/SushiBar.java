package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.card.landmark.harbor.Harbour;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class SushiBar extends Restaurant {
  public SushiBar() {
    diceNumberList = Arrays.asList(1);
    buildingCost = 2;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    if (playerHasBuilt(targetedPlayer, Harbour.class)) {
      super.applyCardEffect(game, targetedPlayer);
    }
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 3;
  }

}
