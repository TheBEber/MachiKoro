package com.whiskas.card.landmark.abs;

import com.whiskas.card.Card;

public abstract class Landmark extends Card {
  protected Boolean isContructible;

  protected Boolean isBuilt;

  public Boolean isContructible() {
    return isContructible;
  }

  public Boolean isBuilt() {
    return isBuilt;
  }

  public void setBuilt(boolean b) {
    isBuilt = b;
  }

}
