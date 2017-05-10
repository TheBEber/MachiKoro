package com.whiskas.main;

import com.whiskas.utils.Display;

public class Main {

  // TODO add easter eggs/animations (UNE MINE !)
  // TODO code IA
  public static void main(String[] args) {
    GameContext game = init();
    game.setCurrentPlayer(0); // TODO (pour le multi ?)
    while (true) {
      Turn.process(game);
      if (game.isFinished()) {
        Display.text("Player " + (game.getCurrentPlayer().getId() + 1) + " win the game !");
        break;
      }
    }
    return;
  }

  private static GameContext init() {
    Integer nbOfPlayers = 4; // Tools.getInteger("number of players");
    Integer maxBoardSlot = 10; // Tools.getInteger("number of cards displayed on the board");
    Integer reducePurple = 1;// Tools.getInteger("to reduce by X the number of purple cards of to number of player");
    boolean harbor = true;
    boolean millionairesRow = true;
    boolean promo = true;
    return new GameContext(nbOfPlayers, maxBoardSlot, reducePurple, harbor, millionairesRow, promo);
    // TODO charger un .properties
  }
}
