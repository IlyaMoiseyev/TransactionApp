package exception;

public class EmptyFileException extends RuntimeException {
  private final String fileName;

  public EmptyFileException(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String getMessage() {
    return "Файл " + fileName + " не содержит данных для осуществления операций перевода.";
  }
}
