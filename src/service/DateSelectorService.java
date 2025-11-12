package service;

import exception.NoMatchingFilesException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateSelectorService {
  private DateSelectorService() {}

  public static void launchDateSelectorService() {
    try {
      Scanner scanner = new Scanner(System.in);
      String dateFrom;
      String dateBy;
      System.out.println("Please enter the dates of the period:");
      System.out.println("Input format: yyyy-mm-dd");
      System.out.println("From what date:");
      dateFrom = scanner.nextLine();
      System.out.println("On what date:");
      dateBy = scanner.nextLine();
      showHistoryByDate(dateFrom, dateBy);
    } catch (NoMatchingFilesException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public static void showHistoryByDate(String dateFrom, String dateBy) throws NoMatchingFilesException {
    LocalDate startDate = LocalDate.parse(dateFrom);
    LocalDate endDate = LocalDate.parse(dateBy);
    StringBuilder historyOfOperations = new StringBuilder();
    File reportFile = new File(PathsStorageService.PATH_TO_REPORT_FILE);
    LocalDate dateInReport = null;
    Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})");
    Matcher matcher;

    if (reportFile.exists()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
          matcher = pattern.matcher(line);
          if (matcher.find()) {
            dateInReport = LocalDate.parse(matcher.group());
          }
          if (dateInReport != null && dateVerification(startDate, endDate, dateInReport)) {
            historyOfOperations.append(line).append("\n");
          }
        }
        printOperation(startDate, endDate, historyOfOperations);
      } catch (IOException ex) {
        System.err.println(ex.getMessage());
      }
    } else {
      throw new NoMatchingFilesException();
    }
  }

  public static boolean dateVerification(LocalDate dateFrom, LocalDate dateBy, LocalDate dateFromTheReport) {
    return (dateFrom.isEqual(dateFromTheReport) || dateFromTheReport.isAfter(dateFrom))
            && dateFromTheReport.isBefore(dateBy) || dateFromTheReport.isEqual(dateBy);
  }

  public static void printOperation(LocalDate dateFrom, LocalDate dateBy, StringBuilder operationHistory) {
    System.out.println("History of operations from: " + dateFrom + " by: " + dateBy);
    System.out.println(operationHistory);
  }
}
