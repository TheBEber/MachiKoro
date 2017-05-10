package com.whiskas.main;

public class Turn {

  public static void process(GameContext game) {
    game.newTurnInit();

    game.generateDices();

    game.applyEffects();

    game.selectCard();

    game.endTurn();
  }
}
