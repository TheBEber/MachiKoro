package com.whiskas.data;

import java.util.ArrayList;

import com.whiskas.card.establishment.abs.Establishment;
import com.whiskas.card.establishment.basic.AppleOrchard;
import com.whiskas.card.establishment.basic.Bakery;
import com.whiskas.card.establishment.basic.BusinessCenter;
import com.whiskas.card.establishment.basic.Cafe;
import com.whiskas.card.establishment.basic.CheeseFactory;
import com.whiskas.card.establishment.basic.ConvenienceStore;
import com.whiskas.card.establishment.basic.FamilyRestaurant;
import com.whiskas.card.establishment.basic.Forest;
import com.whiskas.card.establishment.basic.FruitAndVegetableMarket;
import com.whiskas.card.establishment.basic.FurnitureFactory;
import com.whiskas.card.establishment.basic.Mine;
import com.whiskas.card.establishment.basic.Ranch;
import com.whiskas.card.establishment.basic.Stadium;
import com.whiskas.card.establishment.basic.TVStation;
import com.whiskas.card.establishment.basic.WheatField;
import com.whiskas.card.establishment.harbor.FlowerOrchard;
import com.whiskas.card.establishment.harbor.FlowerShop;
import com.whiskas.card.establishment.harbor.FoodWarehouse;
import com.whiskas.card.establishment.harbor.HamburgerStand;
import com.whiskas.card.establishment.harbor.MackerelBoat;
import com.whiskas.card.establishment.harbor.PizzaJoint;
import com.whiskas.card.establishment.harbor.Publisher;
import com.whiskas.card.establishment.harbor.SushiBar;
import com.whiskas.card.establishment.harbor.TaxOffice;
import com.whiskas.card.establishment.harbor.TunaBoat;
import com.whiskas.card.establishment.millionairesrow.CornField;
import com.whiskas.card.establishment.millionairesrow.DemolitionCompany;
import com.whiskas.card.establishment.millionairesrow.FrenchRestaurant;
import com.whiskas.card.establishment.millionairesrow.GeneralStore;
import com.whiskas.card.establishment.millionairesrow.LoanOffice;
import com.whiskas.card.establishment.millionairesrow.MembersOnlyClub;
import com.whiskas.card.establishment.millionairesrow.MovingCompany;
import com.whiskas.card.establishment.millionairesrow.Park;
import com.whiskas.card.establishment.millionairesrow.RenovationCompany;
import com.whiskas.card.establishment.millionairesrow.SodaBottlingPlant;
import com.whiskas.card.establishment.millionairesrow.TechStartup;
import com.whiskas.card.establishment.millionairesrow.Vineyard;
import com.whiskas.card.establishment.millionairesrow.Winery;
import com.whiskas.card.establishment.promo.InternationalExhibitHall;

public class Deck extends ArrayList<Establishment> {

  private static final long serialVersionUID = 665619051397476869L;

  public Deck(Integer nbOfPlayer, Integer reducePurple, boolean harbor, boolean millionairesRow, boolean promo) {
    for (int i = 0; i < 6; i++) {
      this.add(new WheatField());
      this.add(new Ranch());
      this.add(new Bakery());
      this.add(new Cafe());
      this.add(new ConvenienceStore());
      this.add(new Forest());
      this.add(new CheeseFactory());
      this.add(new FurnitureFactory());
      this.add(new Mine());
      this.add(new FamilyRestaurant());
      this.add(new AppleOrchard());
      this.add(new FruitAndVegetableMarket());
      if (harbor) {
        this.add(new FlowerOrchard());
        this.add(new FlowerShop());
        this.add(new FoodWarehouse());
        this.add(new HamburgerStand());
        this.add(new MackerelBoat());
        this.add(new PizzaJoint());
        this.add(new SushiBar());
        this.add(new TunaBoat());
      }
      if(millionairesRow){
        this.add(new CornField());
        this.add(new DemolitionCompany());
        this.add(new FrenchRestaurant());
        this.add(new GeneralStore());
        this.add(new LoanOffice());
        this.add(new MembersOnlyClub());
        this.add(new MovingCompany());
        this.add(new SodaBottlingPlant());
        this.add(new Vineyard());
        this.add(new Winery());
      }
    }

    for (int j = 0; j < nbOfPlayer - reducePurple; j++) {
      this.add(new Stadium());
      this.add(new BusinessCenter());
      this.add(new TVStation());
      if (harbor) {
        this.add(new Publisher());
        this.add(new TaxOffice());
      }
      if (millionairesRow) {
        this.add(new Park());
        this.add(new RenovationCompany());
        this.add(new TechStartup());
      }
      if (promo) {
        this.add(new InternationalExhibitHall());
      }
    }
  }

}
