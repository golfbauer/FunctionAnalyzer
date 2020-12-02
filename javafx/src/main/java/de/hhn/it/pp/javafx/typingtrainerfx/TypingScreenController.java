package de.hhn.it.pp.javafx.typingtrainerfx;

import de.hhn.it.pp.components.typingtrainer.Feedback;
import de.hhn.it.pp.components.typingtrainer.FileReader;
import de.hhn.it.pp.components.typingtrainer.PracticeText;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerDescriptor;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerService;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TypingScreenController implements Initializable, TypingTrainerService {

  private TypingTrainerDescriptor descriptor; //TEST

  @FXML
  private Button btn_Exit;
  @FXML
  private Button btn_Retry;

  @FXML
  private Label lbl_Time;
  @FXML
  private TextField textfield_practiceText;
  @FXML
  private TextField textfield_typedText;

  @FXML
  private Pane pane_Score;
  @FXML
  private Label lbl_FeedbackTime;
  @FXML
  private Label lbl_FeedbackWPM;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    pane_Score.setVisible(false);
    lbl_Time.setText("00:00");
    textfield_typedText.setText("");
    lbl_FeedbackTime.setText("--:--");
    lbl_FeedbackWPM.setText("--:--");

    textfield_typedText.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode().isWhitespaceKey()) {
          userInput();
        }
      }
    });
  }

  //region Init Methods
  public void initData(String item) throws FileNotFoundException //TEST
  {
    //Audio file holen
    File audioWrongWord = new File("sound_wrongWord.mp3");

    //Feedback holen
    Feedback feedback = new Feedback(0, 0);

    //PracticeText holen
    FileReader fileReader = new FileReader(item);
    String[] selectedText = fileReader.getPracticeText();
    PracticeText practiceText = new PracticeText(selectedText);

    //Descriptor erstellen
    descriptor = new TypingTrainerDescriptor(audioWrongWord, feedback, practiceText);

    //Setup GUI
    textfield_practiceText.setText(singleString(descriptor.getPracticeText().getText()));
  }

  /**
   * Converts String[] to a single String to initialize the textbox
   *
   * @param arrayText String[] that should be converted
   * @return single String of arrayText
   */
  private String singleString(String[] arrayText) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < arrayText.length; i++) {
      sb.append(arrayText[i] + " ");
    }
    return sb.toString();
  }
  //endregion

  //IST IN INTERFACE = QUIT SESSION. MUSS GEÄNDERT WERDEN :)
  public void btnClick_Exit(ActionEvent event) throws IOException {

    Parent typingScreenParent =
        FXMLLoader.load(getClass().getResource("/fxml/typingtrainer/StartScreen.fxml"));
    Scene typingScreenScene = new Scene(typingScreenParent);

    //Stage Info
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(typingScreenScene);
    window.show();

    //wurde ein text ausgewählt
    System.out.println("Wechselt zu startscreen");
  }

  public String[] splitText(String text) {
    return text.split(" ");
  }

  //region TypingTrainerService Interface

  /**
   * Checks if a word is written correctly and calls markWord to either mark the current Word or marks the correct Word
   *
   * @param word  word to check
   * @param index
   * @return true when word is correct
   */
  @Override
  public boolean checkWord(String word, int index) {
    boolean ret = false;
    String wordPT = descriptor.getPracticeText().getWordAtIndex(index);

    //Debug
    System.out.println("word = "+word);
    System.out.println("PT = "+wordPT);

    return word.equals(wordPT) ? true : false;
  }

  /**
   * Plays the sound for wrong Words Game goes brrrrrt.
   */
  @Override
  public void audioOutput() {

  }

  /**
   * Quits learning session and return to main menu
   */
  @Override
  public void quitSession() {

  }

  /**
   * Shows feedback
   *
   * @param feedback feedback to show
   */
  @Override
  public void showFeedback(Feedback feedback) {

  }

  /**
   * saves feedback (score)
   *
   * @param score feedback to save
   */
  @Override
  public void saveScore(Feedback score) {

  }

  /**
   * loads saved feedbacks (scores)
   */
  @Override
  public void loadScore() {

  }

  /**
   * Gets the userinput aka keystrokes through a scanner and is potentially used for Feedback, CheckWord etc
   */
  @Override
  public void userInput() {
    //das vom label 1 + 2
    String[] typedWordsTxtf = splitText(textfield_typedText.getText());
    //überprüfen mit typedWords[currentIndex]
    int currentIndex = descriptor.getPracticeText().getCurrentWordIndex();
    descriptor.addTypedWords(typedWordsTxtf[currentIndex], currentIndex);


    if (checkWord(descriptor.getTypedWordsAtIndex(currentIndex), currentIndex)) {
      markWord(currentIndex, Color.GREEN);
      System.out.println("RICHTIG");
    } else {
      markWord(currentIndex, Color.RED);
      System.out.println("FALSCHÖ");
    }

    descriptor.getPracticeText().increaseCurrentWordIndex();
  }

  /**
   * Print a countdown
   *
   * @param seconds seconds from where to start counting down
   * @throws InterruptedException If an interruption exception occurred
   */
  @Override
  public void countdown(int seconds) throws InterruptedException {

  }

  /**
   * Marks either the currentWord or if the word is written correctly depending on checkWord.
   *
   * @param index
   * @param color
   */
  @Override
  public void markWord(int index, Color color) {
    if (color.equals(Color.GREEN)) {
      textfield_typedText.setStyle("-fx-text-fill: #008000");
    } else {
      textfield_typedText.setStyle("-fx-text-fill: #ff0000");
    }

    System.out.println("MARKED " + color);
  }

  /**
   * Selecting the text you want to train your typing in(preset texts not individual texts from User)
   */
  @Override
  public void selectionOfText() {

  }
  //endregion
}
