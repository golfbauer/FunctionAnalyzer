package de.hhn.it.pp.components.typingtrainer;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class used to to save and load highscores.
 *
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */
public class SaveLoad {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SaveLoad.class);

  public String path = "components/src/main/resources/saveData/highscores.txt";
  public String loadPath = "components/src/main/resources/saveData/highscores.txt";

  /**
   * Appends new text in the existing highscores.txt (adds new scores).
   *
   * @param sessionText text used for practice
   * @param time        time needed for completion
   * @param wpm         words per minute
   * @throws IOException IOException
   */
  public void save(String sessionText, String time, String wpm)
      throws IOException { //   "components/src/main/resources/highscores.txt"

    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter pw = null;

    fw = new FileWriter(path, true);
    bw = new BufferedWriter(fw);
    pw = new PrintWriter(bw);

    pw.print(sessionText + " " + time + " " + wpm + " "); //Schreibt in highscores.txt

    System.out.println("Data Successfully appended into file");

    pw.flush();
    pw.close();
    bw.close();
    fw.close();

  }

  /**
   * Saves data to highscores.txt.
   *
   * @param path path where highscore is
   * @throws IOException Exception
   */
  public void save(String path) throws IOException { //JUnit
    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter pw = null;

    fw = new FileWriter(path, true);
    bw = new BufferedWriter(fw);
    pw = new PrintWriter(bw);

    String sessionText = "selectedtext";
    String time = "1.54";
    String wpm = "2";

    pw.print(sessionText + " " + time + " " + wpm + " "); //Schreibt in highscores.txt

    System.out.println("Data Successfully appended into file");

    pw.flush();

  }

  /**
   * Reads the content of highscores.txt and returns for further use in the gui.
   *
   * @return content of highscores.txt
   */
  public String load() throws IOException {
    String filePath = loadPath;
    String content = "empty";

    content = new String(Files.readAllBytes(Paths.get(filePath)));

    return content;
  }

  /**
   * Loads highscores from given path.
   *
   * @param path path of highscores.txt
   * @return content of highscores
   */
  public String load(String path) throws IOException { //JUnit
    String filePath = path;
    String content = "empty";

    content = new String(Files.readAllBytes(Paths.get(filePath)));

    return content;
  }
}
