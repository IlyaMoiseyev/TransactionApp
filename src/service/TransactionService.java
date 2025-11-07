package service;

import exception.EmptyFileException;
import exception.WrongAmountException;
import model.Account;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionService {
  private final File file;
  private final StringBuilder textFromTheFile;
  private boolean isFind;

  public TransactionService(File file) {
    this.file = file;
    textFromTheFile = FileReaderService.readTheFile(file);
  }

  public void transactionExecutions() throws EmptyFileException {
    Pattern pattern = Pattern.compile("(\\d{5}-\\d{5})\\s(\\d{5}-\\d{5})\\s(-?\\d+.?(\\d+)?)");
    Matcher matcher = pattern.matcher(textFromTheFile);

    while (matcher.find()) {
      isFind = true;
      Account debitingAccount = new Account(matcher.group(1));
      Account creditingAccount = new Account(matcher.group(2));
      double transferAmount = Double.parseDouble(matcher.group(3));
      LocalDate date = LocalDate.now();
      LocalTime time = LocalTime.now();
      String possibleException = null;
      boolean possibilityTransfer = false;

      try {
        possibilityTransfer = transferIsPossible(debitingAccount.getBalance(), transferAmount);
        if (possibilityTransfer) {
          debitingAccount.debitingFunds(transferAmount);
          creditingAccount.balanceReplenishment(transferAmount);
        }
      } catch (WrongAmountException ex) {
        possibleException = ex.getMessage();
      }
      new LoggingService(date, time.truncatedTo(ChronoUnit.MILLIS), file.getName(), debitingAccount.getAccountNumber(),
              creditingAccount.getAccountNumber(), transferAmount, possibilityTransfer, possibleException);
    }
    new FileMovingService(file).writeFileIntoArchiveDirectory();
    if (!isFind) {
      throw new EmptyFileException(file.getName());
    }
  }

  public boolean transferIsPossible(double debitingAccountBalance, double transferAmount) throws WrongThreadException {
    if (transferAmount < 0 || transferAmount > debitingAccountBalance) {
      throw new WrongAmountException(transferAmount, debitingAccountBalance);
    }
    return debitingAccountBalance >= transferAmount;
  }
}
