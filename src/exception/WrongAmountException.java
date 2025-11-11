package exception;

public class WrongAmountException extends RuntimeException {
  private final double transferAmount;
  private final double balance;

  public WrongAmountException(double transferAmount, double balance) {
    this.transferAmount = transferAmount;
    this.balance = balance;
  }

  @Override
  public String getMessage() {
    if (transferAmount < 0) {
      return "Negative transfer amount!";
    } else if (transferAmount > balance) {
      return "Transfer amount exceeds the account balance!";
    } else {
      return "Error with the transfer amount";
    }
  }
}
