package de.hhn.it.pp.components.typingtrainer;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.2
 * @since 1.1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReader {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FileReader.class);

  public String fileContent = "";//String where text from file gets saved

  /**
   * Reads practiceText and provides content.
   * @throws FileNotFoundException Exception if given File is not Found
   */
  public FileReader() throws FileNotFoundException {
    String path = FileReader.class.getResource("/practiceText-3.txt").toString();
    path = path.substring(6); //to make the path usable

    File file = new File(path);
    Scanner scan = new Scanner(file);

    fileContent = "";
    while (scan.hasNextLine()) {
      fileContent = fileContent.concat(scan.nextLine() + "\n");
    }

  }

  /**
   * Same as normal FileReader with a fileName as input.
   * @param fileName name of file to read
   * @throws FileNotFoundException Exception if given File is not Found
   */
  public FileReader(String fileName) throws FileNotFoundException {

    String path = "components/src/main/resources/" + fileName;
    System.out.println("PFAD:" + path);

    File file = new File(path);
    Scanner scan = new Scanner(file, StandardCharsets.UTF_8.name());

    fileContent = "";
    while (scan.hasNextLine()) {
      fileContent = fileContent.concat(scan.nextLine() + "\n");
    }

    //System.out.println(fileContent);
  }

  /**
   * Reads file as other two method via files.
   * @param file given file to read
   * @throws FileNotFoundException Exception if given File is not Found
   */
  public FileReader(File file) throws FileNotFoundException {
    Scanner scan = new Scanner(file, StandardCharsets.UTF_8.name());

    fileContent = "";

    while (scan.hasNextLine()) {
      fileContent = fileContent.concat(scan.nextLine() + "\n");
    }
  }

  /**
   * Splits fileContent in different words.
   * @return fileContent as String[]
   */
  public String[] getPracticeText() {
    return fileContent.split(" ");
  }
}
