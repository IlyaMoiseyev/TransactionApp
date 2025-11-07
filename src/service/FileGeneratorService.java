package service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class FileGeneratorService {
  private static final String[] DEFAULT_FILE_NAMES = {
          "accountsAndAmounts", "paymentRegister", "financialTransferSheet",
          "transferAndBalances", "paymentAccountsSummary", "transferAccountsRegistry2025",
          "accountBalances2025", "accountsSnapshot2025", "balancesAndTransferList",
          "accountsToTransferWithAmount", "accountsWithBalancesForPayment"
  };

  public static void generateFiles(int numberOfFiles) {
    while (numberOfFiles > 0) {
      StringBuilder fillingText = getFillingForTheFile();
      checkingDirectoryExistence();
      try (FileWriter fileWriter = new FileWriter("src\\input\\" + getRandomFileName() + ".txt")) {
        fileWriter.write(String.valueOf(fillingText));
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
      numberOfFiles--;
    }
  }

  public static void checkingDirectoryExistence() {
    Path directory = Paths.get("src", "input");
    if (!Files.exists(directory)) {
      try {
        Files.createDirectories(directory);
      } catch (IOException ex) {
        System.err.println(ex.getMessage());
      }
    }
  }

  public static String getRandomFileName() {
    int counter = DEFAULT_FILE_NAMES.length;
    int randomIndex = new Random().nextInt(DEFAULT_FILE_NAMES.length);
    String fileNamename = DEFAULT_FILE_NAMES[randomIndex];

    if (fileNamename == null) {
      while (counter > 0) {
        randomIndex = new Random().nextInt(DEFAULT_FILE_NAMES.length);
        if (DEFAULT_FILE_NAMES[randomIndex] != null) {
          fileNamename = DEFAULT_FILE_NAMES[randomIndex];
          DEFAULT_FILE_NAMES[randomIndex] = null;
          break;
        }
        counter--;
      }
      if (counter == 0) {
        System.out.println("Свободные имена файлов исчерпаны!");
      }
    } else {
      DEFAULT_FILE_NAMES[randomIndex] = null;
    }
    return fileNamename;
  }

  public static StringBuilder getFillingForTheFile() {
    StringBuilder fillingText = new StringBuilder();
    int numberOfRows = new Random().nextInt(1, 11);

    for (int i = 0; i < numberOfRows; i++) {
      fillingText.append(getPartOfAccountNumber())
              .append("-")
              .append(getPartOfAccountNumber())
              .append(" ")
              .append(getPartOfAccountNumber())
              .append("-")
              .append(getPartOfAccountNumber())
              .append(" ")
              .append(getTransferAmount())
              .append("\n");
    }
    return fillingText;
  }

  public static int getPartOfAccountNumber() {
    return new Random().nextInt(10000, 100000);
  }

  public static double getTransferAmount() {
    double transferAmount = new Random().nextDouble(10, 10000);
    return (double) Math.round(transferAmount * 100) / 100;
  }

  public static int getRandomNumberOfFiles() {
    return new Random().nextInt(2, 6);
  }
}
