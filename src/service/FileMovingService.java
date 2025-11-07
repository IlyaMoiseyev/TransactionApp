package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public record FileMovingService(File file) {
  public void writeFileIntoArchiveDirectory() {
    Path sourceFile = Paths.get(file.getPath());
    Path targetDir = Paths.get("src\\archive\\");
    Path targetFile = targetDir.resolve(sourceFile.getFileName());

    try {
      if (!Files.exists(targetDir)) {
        Files.createDirectory(targetDir);
      }
      Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
