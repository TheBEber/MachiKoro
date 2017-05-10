package com.whiskas.data;

import java.util.ArrayList;
import java.util.List;

import com.whiskas.card.Card;
import com.whiskas.card.establishment.abs.Restaurant;
import com.whiskas.card.establishment.abs.Special;
import com.whiskas.card.establishment.basic.Bakery;
import com.whiskas.card.establishment.basic.WheatField;
import com.whiskas.card.landmark.basic.AmusementPark;
import com.whiskas.card.landmark.basic.RadioTower;
import com.whiskas.card.landmark.basic.ShoppingMall;
import com.whiskas.card.landmark.basic.TrainStation;
import com.whiskas.card.landmark.harbor.Airport;
import com.whiskas.card.landmark.harbor.CityHall;
import com.whiskas.card.landmark.harbor.Harbour;
import com.whiskas.card.landmark.promo.SantaClaussToyManufactory;
import com.whiskas.utils.Display;

public class Player {
  private Integer playerID;

  private Integer gold = 3;

  private List<Card> listCard = new ArrayList<Card>();

  public Player(int i, boolean harbor, boolean promo) {
    playerID = i;
    if (promo) {
      listCard.add(new SantaClaussToyManufactory());
    }
    if (harbor) {
      listCard.add(new CityHall());
      listCard.add(new Harbour());
    }
    listCard.add(new TrainStation());
    listCard.add(new ShoppingMall());
    listCard.add(new AmusementPark());
    listCard.add(new RadioTower());
    if (harbor) {
      listCard.add(new Airport());
    }
    listCard.add(new WheatField());
    listCard.add(new Bakery());
  }

  public Integer getMoney() {
    return gold;
  }

  public void setMoney(int i) {
    gold = i;
    Display.text(Display.getPlayerName(playerID) + " new amount of gold = " + gold);
  }

  public Integer getId() {
    return playerID;
  }

  public List<Card> getListCard() {
    return listCard;
  }

  @SuppressWarnings("unchecked")
  public <T extends Card> T getCard(Class<T> cardClass) {
    for (int i = 0; i < listCard.size(); i++) {
      if (cardClass.isInstance(listCard.get(i))) {
        return (T)listCard.get(i);
      }
    }
    return null;
  }

  public <T extends Card> List<T> getCards(Class<T> cardClass) {
    return getCards(cardClass, false, false);
  }

  @SuppressWarnings("unchecked")
  public <T extends Card> List<T> getCards(Class<T> cardClass, boolean avoidSpecial, boolean avoidRestaurant) {
    ArrayList<T> returnList = new ArrayList<T>();
    for (int i = 0; i < listCard.size(); i++) {
      if (cardClass.isInstance(listCard.get(i))) {
        if (!(avoidSpecial && Special.class.isInstance(listCard.get(i)))
            && !(avoidRestaurant && Restaurant.class.isInstance(listCard.get(i)))) {
          returnList.add((T)listCard.get(i));
        }
      }
    }
    return returnList;
  }
}
