package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Company;
import com.whiskas.card.landmark.abs.Landmark;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;
import com.whiskas.utils.PlayerUtils;

public class DemolitionCompany extends Company {
  public DemolitionCompany() {
    diceNumberList = Arrays.asList(4);
    buildingCost = 2;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player currentplayer) {
    if (PlayerUtils.getLandmarks(currentplayer, true, true).size() > 0) {
      while(true){
        Landmark selectedLandMark = Action.selectALandmarkFrom(currentplayer, true);
        if (selectedLandMark != null) {
          selectedLandMark.setBuilt(false);
          return 8;
        }
      }
    }
    return 0;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentplayer) {
    Action.transferMoneyFromBankToPlayer(currentplayer, moneyToGet(game, currentplayer), getClass());
  }
}
