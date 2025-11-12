package service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public final class FileGeneratorService {
  private static final int MINIMUM_NUMBERS_OF_ROWS = 1;
  private static final int MAXIMUM_NUMBERS_OF_ROWS = 11;
  private static final int MINIMUM_VALUE_OF_ACCOUNT_NUMBER_PART = 10000;
  private static final int MAXIMUM_VALUE_OF_ACCOUNT_NUMBER_PART = 100000;
  private static final int MINIMUM_TRANSFER_AMOUNT = 10;
  private static final int MAXIMUM_TRANSFER_AMOUNT = 10000;
  private static final int ROUNDING_FACTOR = 100;
  private static final int MINIMUM_NUMBER_OF_FILES = 2;
  private static final int MAXIMUM_NUMBER_OF_FILES = 6;
  private static final String FILE_EXTENSION = ".txt";
  private static final String[] DEFAULT_FILE_NAMES = {
          "accountsAndAmounts", "paymentRegister", "financialTransferSheet",
          "transferAndBalances", "paymentAccountsSummary", "transferAccountsRegistry2025",
          "accountBalances2025", "accountsSnapshot2025", "balancesAndTransferList",
          "accountsToTransferWithAmount", "accountsWithBalancesForPayment"
  };

  private FileGeneratorService() {}

  public static void generateFiles(int numberOfFiles) {
    while (numberOfFiles > 0) {
      StringBuilder fillingText = getFillingForTheFile();
      checkingDirectoryExistence();
      try (FileWriter fileWriter = new FileWriter( PathsStorageService.PATH_TO_INPUT_DIRECTORY
              + getRandomFileName()
              + FILE_EXTENSION)) {
        fileWriter.write(String.valueOf(fillingText));
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
      numberOfFiles--;
    }
  }

  public static void checkingDirectoryExistence() {
    Path directory = Paths.get(PathsStorageService.PATH_TO_INPUT_DIRECTORY);
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
        System.out.println("Unoccupied file names are exhausted!");
      }
    } else {
      DEFAULT_FILE_NAMES[randomIndex] = null;
    }
    return fileNamename;
  }

  public static StringBuilder getFillingForTheFile() {
    StringBuilder fillingText = new StringBuilder();
    int numberOfRows = new Random().nextInt(MINIMUM_NUMBERS_OF_ROWS, MAXIMUM_NUMBERS_OF_ROWS);

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
    return new Random().nextInt(MINIMUM_VALUE_OF_ACCOUNT_NUMBER_PART, MAXIMUM_VALUE_OF_ACCOUNT_NUMBER_PART);
  }

  public static double getTransferAmount() {
    double transferAmount = new Random().nextDouble(MINIMUM_TRANSFER_AMOUNT, MAXIMUM_TRANSFER_AMOUNT);
    return (double) Math.round(transferAmount * ROUNDING_FACTOR) / ROUNDING_FACTOR;
  }

  public static int getRandomNumberOfFiles() {
    return new Random().nextInt(MINIMUM_NUMBER_OF_FILES, MAXIMUM_NUMBER_OF_FILES);
  }
}
