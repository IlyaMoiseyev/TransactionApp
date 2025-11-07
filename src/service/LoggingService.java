package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoggingService {
  public LoggingService(LocalDate date,
                        LocalTime time,
                        String fileName,
                        String debitingAccountNumber,
                        String creditingAccountNumber,
                        double transferAmount,
                        boolean operationSuccess,
                        String exception) {
    loggingOperation(date,
            time,
            fileName,
            debitingAccountNumber,
            creditingAccountNumber,
            transferAmount,
            operationSuccess,
            exception);
  }

  public void loggingOperation(LocalDate date,
                               LocalTime time,
                               String fileName,
                               String debitingAccountNumber,
                               String creditingAccountNumber,
                               double transferAmount,
                               boolean operationSuccess,
                               String exception) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\report.txt", true))) {
      String resultOperation = "ошибка!";

      if (operationSuccess) {
        resultOperation = "успешно!";
      }
      writer.write(date + " " + time + " "
              + fileName
              + " перевод со счета: "
              + debitingAccountNumber + ", на счет: "
              + creditingAccountNumber + ", сумма перевода: "
              + transferAmount + " | "
              + resultOperation);

      if (exception != null) {
        writer.write(" | " + exception);
      }
      writer.write("\n");
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
