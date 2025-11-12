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
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(PathsStorageService.PATH_TO_REPORT_FILE, true))) {
      String resultOperation = "fault!";

      if (operationSuccess) {
        resultOperation = "success!";
      }
      writer.write(date + " " + time + " "
              + fileName
              + " transfer from an account: "
              + debitingAccountNumber + ", to the account: "
              + creditingAccountNumber + ", transfer amount: "
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
