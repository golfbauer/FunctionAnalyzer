package de.hhn.it.pp.components.typingtrainer;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.2
 * @since 1.1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReader {
  public String fileContent = "";//String where text from file gets saved

  public FileReader() throws FileNotFoundException {
    String path = FileReader.class.getResource("/practiceText-1.txt").toString();
    path = path.substring(6); //to make the path usable

    File file = new File(path);
    Scanner scan = new Scanner(file);

    fileContent = "";
    while (scan.hasNextLine()) {
      fileContent = fileContent.concat(scan.nextLine() + "\n");
    }

  }

  public FileReader(String fileName) throws FileNotFoundException {

    String path = "components/src/main/resources/"+fileName;
    System.out.println("PFAD:"+path);

    File file = new File(path);
    Scanner scan = new Scanner(file);

    fileContent = "";
    while (scan.hasNextLine()) {
      fileContent = fileContent.concat(scan.nextLine() + "\n");
    }

    //System.out.println(fileContent);
  }

  /**
   * Splits fileContent in different words
   * @return fileContent as String[]
   */
  public String[] getPracticeText() {
    return fileContent.split(" ");
  }
}
