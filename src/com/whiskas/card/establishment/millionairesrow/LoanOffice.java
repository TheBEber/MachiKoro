package com.whiskas.card.establishment.millionairesrow;

import java.util.Arrays;

import com.whiskas.card.establishment.abs.Company;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;
import com.whiskas.utils.Display;

public class LoanOffice extends Company {
  public LoanOffice() {
    diceNumberList = Arrays.asList(5,6);
    buildingCost = 0;
  }

  @Override
  protected Integer moneyToGet(GameContext game, Player targetedPlayer) {
    return 5;
  }

  @Override
  public void applyCardEffect(GameContext game, Player currentPlayer) {
    Integer amountToPay = 2;
    if (currentPlayer.getMoney() == 0) {
      Display.alertNoMoreGold(currentPlayer.getId());
      amountToPay = 0;
    }
    else if (currentPlayer.getMoney() <= amountToPay) {
      amountToPay = currentPlayer.getMoney();
    }

    Display.text(Display.getPlayerName(currentPlayer.getId()) + " paid " + amountToPay +
        " to the bank because of his LoanOffice");
    currentPlayer.setMoney(currentPlayer.getMoney() - amountToPay);
  }

  @Override
  public void onBuy(GameContext game, Player targetedPlayer) {
    Action.transferMoneyFromBankToPlayer(targetedPlayer, moneyToGet(game, targetedPlayer), this.getClass());
  }
}
