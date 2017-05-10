package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Factory;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Winery extends Factory {
  public Winery() {
    diceNumberList = Arrays.asList(9);
    buildingCost = 3;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 6 * targetedPlayer.getCards(Vineyard.class).size();
  }

  @Override
  public void applyCardEffect(GameContext game, Player targetedPlayer) {
    super.applyCardEffect(game, targetedPlayer);
    close();
  }

}
