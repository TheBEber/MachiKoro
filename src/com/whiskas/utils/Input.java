package com.whiskas.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
  public static Integer getInteger(String string) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(" > Enter value of desired " + string + ": ");
    try {
      return Integer.parseInt(br.readLine());
    }
    catch (IOException | NumberFormatException e) {
      return -3;
    }
  }
}
