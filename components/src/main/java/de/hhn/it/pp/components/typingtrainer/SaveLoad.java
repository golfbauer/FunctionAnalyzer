package de.hhn.it.pp.components.typingtrainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */
public class SaveLoad {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SaveLoad.class);

  /**
   * Appends new text in the existing highscores.txt (adds new scores)
   * @param sessionText
   * @param time
   * @param wpm
   * @throws IOException
   */
  public void save(String sessionText, String time, String wpm) throws IOException { //   "components/src/main/resources/highscores.txt"

    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter pw = null;

    try {
      fw = new FileWriter("components/src/main/resources/saveData/highscores.txt", true);
      bw = new BufferedWriter(fw);
      pw = new PrintWriter(bw);

      pw.print(sessionText+" "+time+" "+wpm+" "); //Schreibt in highscores.txt

      System.out.println("Data Successfully appended into file");

      pw.flush();
    } finally {
      try {
        pw.close();
        bw.close();
        fw.close();
      } catch (IOException io) {// can't do anything }
      }
    }

  }

  /**
   * Reads the content of highscores.txt and returns for further use in the gui
   * @return content of highscores.txt
   */
  public String load()
  {
    String filePath = "components/src/main/resources/saveData/highscores.txt";
    String content = "empty";

    try
    {
      content = new String (Files.readAllBytes(Paths.get(filePath)));
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    return content;
  }
}
