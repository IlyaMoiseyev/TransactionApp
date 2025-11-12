package service;

import exception.NoMatchingFilesException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

public final class FileReaderService {
  private FileReaderService(){}

  public static void launchReadFileService() {
    try {
      startProcessingTheFiles(findSuitableFiles((PathsStorageService.PATH_TO_INPUT_DIRECTORY)));
      System.out.println("File parsing is complete!");
    } catch (NoMatchingFilesException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public static File[] findSuitableFiles(String path) throws NoMatchingFilesException {
    File[] files;
    File folder = new File(path);
    FilenameFilter filter = (file, name) -> name.endsWith(".txt");
    String[] suitableFileNames = folder.list(filter);

    assert suitableFileNames != null;
    if (suitableFileNames.length != 0) {
      files = new File[suitableFileNames.length];
      for (int i = 0; i < suitableFileNames.length; i++) {
        files[i] = new File(path + suitableFileNames[i]);
      }
    } else {
      throw new NoMatchingFilesException();
    }
    return files;
  }

  public static void startProcessingTheFiles(File[] files) {
    for (File file : files) {
      new Thread(new MultithreadingFileHandler(file)).start();
    }
  }

  public static StringBuilder readTheFile(File file) {
    StringBuilder textFromTheFile = null;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      textFromTheFile = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        textFromTheFile.append(line).append("\n");
      }
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
    return textFromTheFile;
  }

  public static void printingReportFileContent() throws NoMatchingFilesException {
    File reportFile = new File(PathsStorageService.PATH_TO_REPORT_FILE);

    if (reportFile.exists()) {
      StringBuilder textFromTheReportFile = readTheFile(reportFile);
      System.out.println(textFromTheReportFile);
    } else {
      throw new NoMatchingFilesException();
    }
  }

  public static void launchPrintingReportFileContent() {
    try {
      printingReportFileContent();
    } catch (NoMatchingFilesException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
