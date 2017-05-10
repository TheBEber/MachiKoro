package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Store;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.PlayerUtils;

public class GeneralStore extends Store {
  public GeneralStore() {
    diceNumberList = Arrays.asList(2);
    buildingCost = 0;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 2;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    if (PlayerUtils.getLandmarks(targetedPlayer, true, true).size() < 2) {
      super.applyCardEffect(game, targetedPlayer);
    }
  }
}
