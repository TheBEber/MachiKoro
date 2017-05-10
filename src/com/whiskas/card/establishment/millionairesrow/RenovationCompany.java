package com.whiskas.card.establishment.millionairesrow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.card.establishment.abs.Special;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;
import com.whiskas.utils.Display;
import com.whiskas.utils.Input;

public class RenovationCompany extends Special {
  public RenovationCompany() {
    diceNumberList = Arrays.asList(8);
    buildingCost = 4;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player currentPlayer) {
    while (true) {
      Integer amount = 0;
      List<ArrayList<Establishment>> allEstablishementMergedList = Display.allEstablishementOnTableSorted(game);
      int id = Input.getInteger("Select an Establishement to close (from RenovationCompany) : ");
      if (id >= 0 && id < allEstablishementMergedList.size()) {
        for (int j = 0; j < allEstablishementMergedList.get(id).size(); j++) {
          allEstablishementMergedList.get(id).get(j).close();
          amount++;
        }

      }
      return amount;
    }
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentplayer) {
    Action.transferMoneyFromBankToPlayer(currentplayer, moneyToGet(game, currentplayer), getClass());
  }
}
