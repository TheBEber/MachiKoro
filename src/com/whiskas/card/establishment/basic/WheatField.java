package com.whiskas.card.establishment.basic;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Field;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class WheatField extends Field {
  public WheatField() {
    diceNumberList = Arrays.asList(1);
    buildingCost = 1;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 1;
  }

}
