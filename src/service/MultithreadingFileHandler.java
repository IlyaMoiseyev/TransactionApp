package service;

import exception.EmptyFileException;

import java.io.File;

public record MultithreadingFileHandler(File file) implements Runnable {
  private void startProcessingTheFile(File file) {
    try {
      new TransactionService(file).transactionExecutions();
    } catch (EmptyFileException ex) {
      System.err.println(ex.getMessage());
    }
  }

  @Override
  public void run() {
    startProcessingTheFile(file);
  }
}
