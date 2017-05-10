package com.whiskas.main;

import java.util.ArrayList;
import java.util.List;

import com.whiskas.card.establishment.abs.BlueCard;
import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.card.establishment.abs.GreenCard;
import com.whiskas.card.establishment.abs.PurpleCard;
import com.whiskas.card.establishment.abs.RedCard;
import com.whiskas.card.establishment.abs.Special;
import com.whiskas.card.landmark.abs.Landmark;
import com.whiskas.card.landmark.basic.AmusementPark;
import com.whiskas.card.landmark.basic.RadioTower;
import com.whiskas.card.landmark.basic.TrainStation;
import com.whiskas.card.landmark.harbor.Airport;
import com.whiskas.card.landmark.harbor.CityHall;
import com.whiskas.card.landmark.harbor.Harbour;
import com.whiskas.data.Board;
import com.whiskas.data.Player;
import com.whiskas.utils.Action;
import com.whiskas.utils.Display;
import com.whiskas.utils.Input;

public class GameContext {

  private List<Player> listOfPlayer = new ArrayList<Player>();

  private Board board;

  private Integer currentDice;

  private Integer tunaBoatRollResult;

  private Player currentPlayer;

  private boolean playAnotherTurn = false;

  private boolean hasbuildSomething = false;

  public GameContext(Integer nbPlayer, Integer maxBoardSlot, Integer reducePurple, boolean harbor,
      boolean millionairesRow, boolean promo) {
    board = new Board(nbPlayer, maxBoardSlot, reducePurple, harbor, millionairesRow, promo);
    for (int i = 0; i < nbPlayer; i++) {
      listOfPlayer.add(new Player(i, harbor, promo));
    }
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(int i) {
    currentPlayer = listOfPlayer.get(i);
  }

  public Board getBoard() {
    return board;
  }

  public Integer getCurrentDice() {
    return currentDice;
  }

  public void generateDices() {
    if (!currentPlayer.getCard(TrainStation.class).isBuilt()) {
      currentDice = Action.roll1D6(this);
      Display.text("\nROLL RESULT >>> " + getCurrentDice() + "\n");
    }
    else {
      rolldices();
      if (currentPlayer.getCard(RadioTower.class).isBuilt()) {
        Integer choice = Input.getInteger(": 1 to reroll dices (from RadioTower)");
        if (choice == 1) {
          rolldices();
        }
      }
      if (currentDice >= 10 && currentPlayer.getCard(Harbour.class) != null &&
          currentPlayer.getCard(Harbour.class).isBuilt()) {
        Integer choice = Input.getInteger(": 1 to add 2 to current roll (from Harbour)");
        if (choice == 1) {
          currentDice += 2;
        }
      }
    }
  }

  public void rolldices() {
    Integer choice = 0;
    Integer roll1 = Action.roll1D6(this);
    Integer roll2 = Action.roll1D6(this);
    playAnotherTurn = false;
    while (choice != 1 || choice != 2) {
      choice = Input.getInteger(": roll 1 or 2 D6 ? (from TrainStation)");
      if (choice == 1) {
        currentDice = roll1;
        break;
      }
      else if (choice == 2) {
        currentDice = roll1 + roll2;
        if (roll1 == roll2) {
          Display.text("You rolled a double !");
          if (currentPlayer.getCard(AmusementPark.class).isBuilt()) {
            Display.text("You earn a free turn after this one if you do NOT reroll the dices! (from AmusementPark)");
            playAnotherTurn = true;
          }
        }
        Display.text("\nROLL RESULT >>> " + getCurrentDice() + "  ( " + roll1 + " + " + roll2 + " )\n");
        return;
      }
      Display.text("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    }
    Display.text("\nROLL RESULT >>> " + getCurrentDice() + "\n");

  }

  public Integer getTunaBoatRollResult() {
    if (tunaBoatRollResult == 0) {
      Integer roll1 = Action.roll1D6(this);
      Integer roll2 = Action.roll1D6(this);
      tunaBoatRollResult = roll1 + roll2;
      return tunaBoatRollResult;
    }
    return tunaBoatRollResult;
  }

  public void newTurnInit() {
    playAnotherTurn = false;
    hasbuildSomething = false;
    tunaBoatRollResult = 0;
    Display.newTurn(this);
  }

  public void applyEffects() {
    applyRedEffects();
    applyGreenEffects();
    applyBlueEffects();
    applyPurpleEffects();
    if (currentPlayer.getMoney() == 0 && currentPlayer.getCard(CityHall.class) != null) {
      Display.text(Display.getPlayerName(currentPlayer.getId()) + " received 1 from CityHall");
      currentPlayer.setMoney(currentPlayer.getMoney() + 1);
    }
  }

  private void applyRedEffects() {
    Integer idplayer = (getCurrentPlayer().getId() + listOfPlayer.size() - 1) % listOfPlayer.size();
    for (int i = listOfPlayer.size() - 1; i > 0; i--) {
      Player targetedPlayer = listOfPlayer.get(idplayer);
      List<RedCard> listOfTargetedPlayerRedCards = targetedPlayer.getCards(RedCard.class);
      for (int j = 0; j < listOfTargetedPlayerRedCards.size(); j++) {
        listOfTargetedPlayerRedCards.get(j).applyEffect(this, targetedPlayer);
      }
      idplayer = (idplayer + listOfPlayer.size() - 1) % listOfPlayer.size();
    }
  }

  private void applyGreenEffects() {
    List<GreenCard> listOfCurrentPlayerGreenCards = currentPlayer.getCards(GreenCard.class);
    for (int j = 0; j < listOfCurrentPlayerGreenCards.size(); j++) {
      listOfCurrentPlayerGreenCards.get(j).applyEffect(this, currentPlayer);
    }
  }

  private void applyBlueEffects() {
    int idplayer = currentPlayer.getId();
    for (int i = 0; i < listOfPlayer.size(); i++) {
      Player targetedPlayer = listOfPlayer.get(idplayer % listOfPlayer.size());
      List<BlueCard> listOfTargetedPlayerBlueCards = targetedPlayer.getCards(BlueCard.class);
      for (int j = 0; j < listOfTargetedPlayerBlueCards.size(); j++) {
        listOfTargetedPlayerBlueCards.get(j).applyEffect(this, targetedPlayer);
      }
      idplayer++;
    }
  }

  private void applyPurpleEffects() {
    List<PurpleCard> listOfCurrentPlayerPurpleCards = currentPlayer.getCards(PurpleCard.class);
    for (int j = 0; j < listOfCurrentPlayerPurpleCards.size(); j++) {
      listOfCurrentPlayerPurpleCards.get(j).applyEffect(this, currentPlayer);
    }
  }

  public List<Player> getListOfPlayers() {
    return listOfPlayer;
  }

  public void selectCard() {
    while (true) {
      Display.allPlayersStatus(this);
      Display.board(board);
      Integer slotId = Input.getInteger("establishment, -1 to build a Monument, -2 to pass turn");
      if (slotId >= 0 && slotId < board.size()) {
        if (!board.get(slotId).isEmpty()) {
          Establishment desiredCard = board.get(slotId).get(board.get(slotId).size() - 1);
          if (currentPlayer.getMoney() >= desiredCard.getBuildingCost()) {
            if (!(Special.class.isInstance(desiredCard) && currentPlayer.getCard(desiredCard.getClass()) != null)) {
              currentPlayer.setMoney(currentPlayer.getMoney() - desiredCard.getBuildingCost());
              currentPlayer.getListCard().add(desiredCard);
              hasbuildSomething = true;
              Display.text(Display.getPlayerName(currentPlayer.getId()) + " have a new " +
                  desiredCard.getClass().getSimpleName());
              board.get(slotId).remove(board.get(slotId).size() - 1);
              desiredCard.onBuy(this, currentPlayer);
              if (board.get(slotId).isEmpty()) {
                board.fillCardSlotsFromDeck();
              }
              return;
            }
            Display.text("Maximum 1 Special Card per player !");
          }
          if (currentPlayer.getMoney() < desiredCard.getBuildingCost()) {
            Display.text("Not enough money !!!");
          }
        }
        else {
          Display.text("Dude...This is an empty slot...");
        }
      }
      else if (slotId == -1) {
        Landmark landMarkToBuild = Action.selectALandmarkFrom(currentPlayer, false);
        if (!landMarkToBuild.isBuilt() && currentPlayer.getMoney() >= landMarkToBuild.getBuildingCost()) {
          currentPlayer.setMoney(currentPlayer.getMoney() - landMarkToBuild.getBuildingCost());
          landMarkToBuild.setBuilt(true);
          hasbuildSomething = true;
          Display.text(Display.getPlayerName(currentPlayer.getId()) + " have a new " +
              landMarkToBuild.getClass().getSimpleName());
          return;
        }
        Display.text("Not enough money or already built !!!");
      }
      else if (slotId == -2) {
        Display.text(Display.getPlayerName(currentPlayer.getId()) + " didn't build anything this turn !");
        return;
      }
      else {
        Display.text("What are you doing ?");
      }
    }
  }

  public boolean isFinished() {
    List<Landmark> listOfMonument = currentPlayer.getCards(Landmark.class);
    for (int i = 0; i < listOfMonument.size(); i++) {
      if (!listOfMonument.get(i).isBuilt()) {
        return false;
      }
    }
    return true;
  }

  public void endTurn() {
    if (!isFinished()) {
      if (!hasbuildSomething && currentPlayer.getCard(Airport.class) != null &&
          currentPlayer.getCard(Airport.class).isBuilt()) {
        Display.text("Get 10 from Airpot");
        currentPlayer.setMoney(currentPlayer.getMoney() + 10);
      }
      if (!playAnotherTurn) {
        setCurrentPlayer((getCurrentPlayer().getId() + 1) % listOfPlayer.size());
      }
    }
  }
}
