package main.java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriterReader {
  private static final String FILE_NAME = "output.txt";
  private static final String EXIT = "-q";
  private static final String LINE_SEPARATOR = System.lineSeparator();

  public static void main(String[] args) {
    Path filePath = Paths.get(FILE_NAME);

    try {
      if (Files.exists(filePath)) {
        Files.delete(filePath);
      }
      Files.createFile(filePath);
      System.out.println("Created file: " + filePath.toAbsolutePath());
    } catch (IOException e) {
      System.err.println("Error creating file: " + e.getMessage());
      return;
    }

    try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
      String userInput;
      System.out.println("Type your sentences. Type '-q' to exit.");

      while (true) {
        System.out.print("Write a sentence to be written in the file: ");
        userInput = keyboard.readLine();

        if (EXIT.equals(userInput)) {
          System.out.println("Exiting the loop.");
          break;
        }

        try {
          Files.writeString(filePath, userInput + LINE_SEPARATOR, StandardOpenOption.APPEND);
        } catch (IOException e) {
          System.err.println("Error writing to file: " + e.getMessage());
        }
      }

      System.out.println("===================");
      System.out.println("Content written: ");
      System.out.println();

      System.out.println("Content of the file:");
      Files.lines(filePath).forEach(System.out::println);

      System.out.println("===================");
      System.out.println("Exiting...");
    } catch (IOException e) {
      System.err.println("Error reading from keyboard: " + e.getMessage());
    }
  }
}
