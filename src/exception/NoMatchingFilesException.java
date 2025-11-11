package exception;

public class NoMatchingFilesException extends RuntimeException {
  @Override
  public String getMessage() {
    return "No suitable files in the directory!";
  }
}
