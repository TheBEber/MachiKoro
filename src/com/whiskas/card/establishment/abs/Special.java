package com.whiskas.card.establishment.abs;

import com.whiskas.data.Player;
import com.whiskas.main.GameContext;
import com.whiskas.utils.Action;

public abstract class Special extends PurpleCard {

  protected void pickMoneyOnAllPlayers(GameContext game) {
    Integer idplayer = (game.getCurrentPlayer().getId() + game.getListOfPlayers().size() - 1) %
        game.getListOfPlayers().size();
    for (int i = game.getListOfPlayers().size() - 1; i > 0; i--) {
      Player targetedPlayer = game.getListOfPlayers().get(idplayer);
      Action
          .transferMoneyFromPlayerToPlayer(targetedPlayer, game.getCurrentPlayer(),
              moneyToGet(game, targetedPlayer), getClass());
      idplayer = (idplayer + game.getListOfPlayers().size() - 1) % game.getListOfPlayers().size();
    }
  }
}
