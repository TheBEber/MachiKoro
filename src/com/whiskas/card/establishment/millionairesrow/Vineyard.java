package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Production;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Vineyard extends Production {
  public Vineyard() {
    diceNumberList = Arrays.asList(7);
    buildingCost = 3;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 3;
  }

}
