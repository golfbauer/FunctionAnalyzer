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

  /**
   * Appends new text in the existing highscores.txt (adds new scores).
   *
   * @param sessionText text used for practice
   * @param time        time needed for completion
   * @param wpm         words per minute
   * @throws IOException IOException
   */
  public void save(String sessionText, String time, String wpm)
      throws IOException,
      FileNotFoundException { //   "components/src/main/resources/highscores.txt"

    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter pw = null;

    try {
      fw = new FileWriter("components/src/main/resources/saveData/highscores.txt", true);
      bw = new BufferedWriter(fw);
      pw = new PrintWriter(bw);

      pw.print(sessionText + " " + time + " " + wpm + " "); //Schreibt in highscores.txt

      System.out.println("Data Successfully appended into file");

      pw.flush();
    } catch (FileNotFoundException fe){

    } catch (IOException io){

    }
    finally {
      try {
        pw.close();
        bw.close();
        fw.close();
      } catch (FileNotFoundException fe) {
      } catch (IOException io) { // can't do anything
      }
    }

  }

  /**
   * Saves data to highscores.txt.
   *
   * @param path path where highscore is
   * @throws IOException Exception
   */
  public void save(String path) throws IOException, FileNotFoundException { //JUnit

    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter pw = null;

    try {
      fw = new FileWriter(path, true);
      bw = new BufferedWriter(fw);
      pw = new PrintWriter(bw);

      String sessionText = "selectedtext";
      String time = "1.54";
      String wpm = "2";

      pw.print(sessionText + " " + time + " " + wpm + " "); //Schreibt in highscores.txt

      System.out.println("Data Successfully appended into file");

      pw.flush();
    } catch (FileNotFoundException fe) {

    } catch (IOException io) {

    } finally {
      //      try {
      //        pw.close();
      //        bw.close();
      //        fw.close();
      //      } catch (IOException io) {// can't do anything }
      //      }
    }

  }

  /**
   * Reads the content of highscores.txt and returns for further use in the gui.
   *
   * @return content of highscores.txt
   */
  public String load() throws FileNotFoundException, IOException {
    String filePath = "components/src/main/resources/saveData/highscores.txt";
    String content = "empty";

    try {
      content = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (FileNotFoundException fe) {

    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return content;
  }

  /**
   * Loads highscores from given path.
   *
   * @param path path of highscores.txt
   * @return content of highscores
   */
  public String load(String path) throws FileNotFoundException, IOException { //JUnit
    String filePath = path;
    String content = "empty";

    try {
      content = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (FileNotFoundException e) {

    } catch (IOException e) {
      e.printStackTrace();
    }

    return content;
  }
}
