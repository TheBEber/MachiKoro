package com.whiskas.card.establishment.abs;

import java.util.List;

import com.whiskas.card.Card;
import com.whiskas.card.landmark.abs.Landmark;
import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Display;

public abstract class Establishment extends Card {

  protected List<Integer> diceNumberList;

  protected Boolean isClosed = false;

  protected abstract Integer moneyToGet(GameContext game, Player targetedPlayer);

  public void applyEffect(GameContext game, Player targetedPlayer) {
    if (diceNumberList.contains(game.getCurrentDice())) {
      if (isClosed) {
        isClosed = false;
        Display.text(getClass().getSimpleName() + " is now opened !");
      }
      else {
        applyCardEffect(game, targetedPlayer);
      }
    }
  };

  public abstract void applyCardEffect(GameContext game, Player targetedPlayer);

  public String getDiceString() {
    String returnString = diceNumberList.get(0).toString();
    if (diceNumberList.size() > 1) {
      returnString += " " + diceNumberList.get(1);
    }
    if (diceNumberList.size() > 2) {
      returnString += " " + diceNumberList.get(2);
    }
    return returnString;
  }

  protected <T extends Landmark> boolean playerHasBuilt(Player player, Class<T> cardClass) {
    if ((player.getCard(cardClass)).isBuilt()) {
      return true;
    }
    return false;
  }

  public List<Integer> getDiceNumberList() {
    return diceNumberList;
  }

  public void onBuy(GameContext game, Player targetedPlayer) {
  }

  public void close() {
    isClosed = true;
    Display.text(getClass().getSimpleName() + " has been closed for renovation !");
  }
}
