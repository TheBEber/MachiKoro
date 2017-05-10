package com.whiskas.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.whiskas.card.Card;
import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.card.landmark.abs.Landmark;
import com.whiskas.card.landmark.promo.SantaClaussToyManufactory;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;

public class Action {
  public static Landmark selectALandmarkFrom(Player targetedPlayer, boolean onlyBuilt) {
    while (true) {
      List<Landmark> landmarkList = Display.landmarks(targetedPlayer, onlyBuilt);
      int id = Input.getInteger("landmarkID");
      if (id >= 0 && id < landmarkList.size()) {
        return landmarkList.get(id);
      }
    }
  }

  public static <T extends Card> Player selectAPlayerById(GameContext game, Class<T> cardClass,
      boolean canSelectYourself, boolean cancelEnable) {
    while (true) {
      String msg = "Select a player by ID (for " + cardClass.getSimpleName() + ")";
      if (cancelEnable) {
        msg += ", 0 to cancel";
      }
      int id = Input.getInteger(msg);
      if (id == 0 && cancelEnable) {
        return null;
      }
      if (id > 0 && id <= game.getListOfPlayers().size()) {
        if (id - 1 == game.getCurrentPlayer().getId() && !canSelectYourself) {
          Display.text("You can't pick yourself as a choice.");
        }
        else {
          return game.getListOfPlayers().get(id - 1);
        }
      }
    }
  }

  public static <T extends Card> void transferMoneyFromPlayerToPlayer(Player playerSource, Player playerDestination,
      Integer maxAmount, Class<T> cardClass) {
    Integer moneyToTranfert = maxAmount;
    if (playerSource.getMoney() == 0) {
      Display.alertNoMoreGold(playerSource.getId());
      moneyToTranfert = 0;
      return;
    }
    else if (playerSource.getMoney() <= maxAmount) {
      moneyToTranfert = playerSource.getMoney();
    }
    Display.goldTransfer(playerDestination.getId(), moneyToTranfert, Display.getPlayerName(playerSource.getId()) +
        " because of his " + cardClass.getSimpleName());
    playerSource.setMoney(playerSource.getMoney() - moneyToTranfert);
    playerDestination.setMoney(playerDestination.getMoney() + moneyToTranfert);

  }

  public static <T extends Card> void transferMoneyFromBankToPlayer(Player player, Integer amount, Class<T> cardClass) {
    Display.goldTransfer(player.getId(), amount, cardClass.getSimpleName());
    player.setMoney(player.getMoney() + amount);
  }

  public static Establishment transferEstablishmentFromPlayerToPlayer(Player playerSource, Player playerDestination) {
    Establishment cardToTransfer = null;
    List<ArrayList<Establishment>> sortedListSource = Display.listOfEtablishementFromPlayer(playerSource, true, false);
    Integer cardID = Input.getInteger(" cardID to transfer from " + Display.getPlayerName(playerSource.getId()) +
        " to " + Display.getPlayerName(playerDestination.getId()));
    if (cardID >= 0 && cardID < sortedListSource.size()) {
      cardToTransfer = sortedListSource.get(cardID).get(sortedListSource.get(cardID).size() - 1);
      playerDestination.getListCard().add(cardToTransfer);
      playerSource.getListCard().remove(cardToTransfer);
      Display.text(Display.getPlayerName(playerSource.getId()) + " gave his " +
          cardToTransfer.getClass().getSimpleName() + " to " + Display.getPlayerName(playerDestination.getId()));
    }
    return cardToTransfer;
  }

  public static Integer roll1D6(GameContext game) {
    if (game.getCurrentPlayer().getCard(SantaClaussToyManufactory.class) != null) {
      Random santaClaussGenerator = new Random();
      if (santaClaussGenerator.nextInt(100) == 25) {
        Display
            .text("Oh oh oh, your dice has been broken ! Let me give you this special gift before reroll! (from SantaClaussToyManufactory)");
        game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() + 3);
      }
    }
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(6) + 1;
  }
}
