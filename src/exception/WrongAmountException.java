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
      return "Отрицательная сумма перевода!";
    } else if (transferAmount > balance) {
      return "Сумма перевода превышает баланс счета!";
    } else {
      return "Ошибка с суммой перевода";
    }
  }
}
