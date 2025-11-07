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
    System.out.println("Система выполнения транзакций запущена!");
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
    System.out.println("Пожалуйста сделайте выбор:");
    System.out.println("1 - запуск парсинга файлов.");
    System.out.println("2 - вывод списка транзакций из файл-отчета.");
    System.out.println("3 - вывод списка транзакций за определенный период.");
    System.out.println("0 - выход из приложения.");
  }
}
