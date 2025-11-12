package service;

import java.util.Scanner;

public final class ApplicationLaunchService {
  private ApplicationLaunchService(){}

  public static void main(String[] args) {
    launchApplication();
  }

  public static void launchApplication() {
    FileGeneratorService.generateFiles(FileGeneratorService.getRandomNumberOfFiles());
    Scanner scanner = new Scanner(System.in);
    System.out.println("Transaction execution system is running!");
    boolean isAlive = true;

    while (isAlive) {
      printNavigationTroughAppFunctionality();
      int usersChoice = scanner.nextInt();
      switch (usersChoice) {
        case 1 -> FileReaderService.launchReadFileService();
        case 2 -> FileReaderService.launchPrintingReportFileContent();
        case 3 -> DateSelectorService.launchDateSelectorService();
        case 0 -> isAlive = false;
      }
    }
    scanner.close();
  }

  private static void printNavigationTroughAppFunctionality() {
    System.out.println("Please make your choice:");
    System.out.println("1 - starting file parsing.");
    System.out.println("2 - view a list of transactions from a report file.");
    System.out.println("3 - view a list of transactions for a specific period.");
    System.out.println("0 - exiting the app.");
  }
}
