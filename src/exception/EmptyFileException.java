package exception;

public class EmptyFileException extends RuntimeException {
  private final String fileName;

  public EmptyFileException(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String getMessage() {
    return "File " + fileName + " does not contain data for performing transfer operations.";
  }
}
