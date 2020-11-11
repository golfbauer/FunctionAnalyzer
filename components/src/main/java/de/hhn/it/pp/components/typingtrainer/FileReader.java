package de.hhn.it.pp.components.typingtrainer;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
  public String fileContent = "";//String where text from file gets saved

  public FileReader() throws FileNotFoundException {
    String path = FileReader.class.getResource("text1.txt").toString();
    path = path.substring(6); //to make the path usable

    File file = new File(path);
    Scanner scan = new Scanner(file);

    fileContent = "";
    while (scan.hasNextLine()) {
      fileContent = fileContent.concat(scan.nextLine() + "\n");
    }

    //System.out.println(fileContent);
  }

  public FileReader(String fileName) throws FileNotFoundException {
    String path = FileReader.class.getResource(fileName).toString();
    path = path.substring(6); //to make the path usable

    File file = new File(path);
    Scanner scan = new Scanner(file);

    fileContent = "";
    while (scan.hasNextLine()) {
      fileContent = fileContent.concat(scan.nextLine() + "\n");
    }

    System.out.println(fileContent);
  }

  /**
   * Splits fileContent in different words
   * @return fileContent as String[]
   */
  public String[] GetPracticeText() {
    return fileContent.split(" ");
  }
}
