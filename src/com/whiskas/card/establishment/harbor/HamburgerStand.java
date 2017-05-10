package com.whiskas.card.establishment.harbor;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class HamburgerStand extends Restaurant {
  public HamburgerStand() {
    diceNumberList = Arrays.asList(8);
    buildingCost = 1;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }
}
