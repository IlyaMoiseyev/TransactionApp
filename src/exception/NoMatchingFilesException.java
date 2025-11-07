package exception;

public class NoMatchingFilesException extends RuntimeException {
  @Override
  public String getMessage() {
    return "В директории отсутствуют подходящие файлы!";
  }
}
