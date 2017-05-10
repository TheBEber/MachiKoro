package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.PlayerUtils;

public class FrenchRestaurant extends Restaurant {
  public FrenchRestaurant() {
    diceNumberList = Arrays.asList(5);
    buildingCost = 3;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 5;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    if (PlayerUtils.getLandmarks(targetedPlayer, true, true).size() >= 2) {
      super.applyCardEffect(game, targetedPlayer);
    }
  }
}
