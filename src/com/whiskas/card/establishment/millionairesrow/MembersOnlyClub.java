package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.PlayerUtils;

public class MembersOnlyClub extends Restaurant {
  public MembersOnlyClub() {
    diceNumberList = Arrays.asList(12, 13, 14);
    buildingCost = 4;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return game.getCurrentPlayer().getMoney();
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    if (PlayerUtils.getLandmarks(targetedPlayer, true, true).size() >= 3) {
      super.applyCardEffect(game, targetedPlayer);
    }
  }
}
