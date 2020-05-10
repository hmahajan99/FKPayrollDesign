package app;

import java.time.LocalDate;

public class Card {
  public LocalDate date;
  public double amount;
  public String message;

  public Card(LocalDate date, double amount, String message) {
    this.date = date;
    this.amount = amount;
    this.message = message;
  }
}