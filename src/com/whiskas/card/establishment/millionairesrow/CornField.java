package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Production;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.PlayerUtils;

public class CornField extends Production {
  public CornField() {
    diceNumberList = Arrays.asList(3, 4);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    if (PlayerUtils.getLandmarks(targetedPlayer, true, true).size() < 2) {
      super.applyCardEffect(game, targetedPlayer);
    }
  }
}
