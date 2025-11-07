package model;

import java.util.Random;

public class Account {
  private final String accountNumber;
  private double balance;

  public Account(String accountNumber) {
    this.accountNumber = accountNumber;
    balance = (double) Math.round(new Random().nextDouble(1, 10000) * 100) / 100;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return (double) Math.round(balance * 100) / 100;
  }

  public void balanceReplenishment(double amount) {
    balance += amount;
  }

  public void debitingFunds(double amount) {
    balance -= amount;
  }
}
