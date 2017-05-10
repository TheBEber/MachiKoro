package com.whiskas.data;

import java.util.ArrayList;
import java.util.Random;

import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.utils.Display;

public class Board extends ArrayList<ArrayList<Establishment>> {
  private static final long serialVersionUID = 4668372152925985703L;

  Integer maxBoardSlot;

  Deck deck;

  public Integer maxBoardSlot() {
    return maxBoardSlot;
  }

  public Board(Integer nbPlayer, Integer maxBoardSlot, Integer reducePurple, boolean harbor, boolean millionairesRow,
      boolean promo) {
    super();
    this.maxBoardSlot = maxBoardSlot;
    this.deck = new Deck(nbPlayer, reducePurple, harbor, millionairesRow, promo);
    for (int i = 0; i < maxBoardSlot; i++) {
      this.add(new ArrayList<Establishment>());
    }
    for (int i = 0; i < maxBoardSlot; i++) {
      fillCardSlotsFromDeck();
    }
  }

  public void fillCardSlotsFromDeck() {
    int deckCardID = 0;
    if (deck.isEmpty()) {
      Display.text("No more cards available on deck");
      return;
    }
    Random randomGeneratorRandom = new Random();
    deckCardID = randomGeneratorRandom.nextInt(deck.size());
    for (int i = 0; i < maxBoardSlot; i++) {
      if (!this.get(i).isEmpty() && deck.get(deckCardID).getClass().isInstance(this.get(i).get(0))) {
        this.get(i).add(deck.get(deckCardID));
        deck.remove(deckCardID);
        fillCardSlotsFromDeck();
        return;
      }
    }
    for (int j = 0; j < maxBoardSlot; j++) {
      if (this.get(j).isEmpty()) {
        this.get(j).add(deck.get(deckCardID));
        deck.remove(deckCardID);
        return;
      }
    }
  }
}
