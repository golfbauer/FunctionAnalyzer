package de.hhn.it.pp.components.typingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.pp.components.typingtrainer.Feedback;
import de.hhn.it.pp.components.typingtrainer.FeedbackNotFoundException;
import de.hhn.it.pp.components.typingtrainer.FileReader;
import de.hhn.it.pp.components.typingtrainer.PracticeText;
import de.hhn.it.pp.components.typingtrainer.SaveData;
import de.hhn.it.pp.components.typingtrainer.SaveLoad;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerDescriptor;
import de.hhn.it.pp.components.typingtrainer.provider.ProviderTypingTrainer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Goodcase Tests.
 *
 * @author Tobias Maraci, Robert Pistea
 * @version 1.1
 * @since 1.0
 */

@DisplayName("Typing Trainer Tests")
public class TestsTypingTrainerGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestsTypingTrainerGoodCases.class);

  ProviderTypingTrainer typingTrainerService;
/*  FileReader testFileReader;
  File testAudioFile;
  String[] testPractice;
  String[] testTyped;
  Feedback testFeedback;
  String testSelectedText;
  PracticeText testPracticeText;
  TypingTrainerDescriptor testDescriptor;*/
  TypingTrainerDescriptor descriptor;
  File audioWrongWord;
  Feedback feedback;
  FileReader fileReader;
  String[] selectedText;
  PracticeText practiceText;

  @BeforeEach
  void initialize() throws IOException, LineUnavailableException,
      UnsupportedAudioFileException, InterruptedException{
    audioWrongWord = new File("javafx/src/main/resources/typingTrainerFiles/8BIT RETRO Beep.mp3");

    feedback = new Feedback(0,0);

    ClassLoader classLoader;
    classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("practiceText-3.txt").getFile());

    fileReader = new FileReader(file);
    selectedText = fileReader.getPracticeText();
    practiceText = new PracticeText(selectedText);

    descriptor = new TypingTrainerDescriptor(audioWrongWord, feedback, practiceText);

    typingTrainerService = new ProviderTypingTrainer();
    typingTrainerService.descriptor = descriptor;
  }

  //region Interface Tests

  @Test
  @DisplayName("Checks if written word matches with word from practice text.")
  void testCheckWord() {
    String wordToCheck = "Die";
    int wordFromPracticeText = 0;

    assertTrue(typingTrainerService.checkWord(wordToCheck, wordFromPracticeText));
  }

  @Test
  @DisplayName("Checks if showFeedback gives the correct output.")
  void testShowFeedback() throws IOException, FeedbackNotFoundException {
    typingTrainerService.showFeedback(typingTrainerService.descriptor.getFeedback());
    typingTrainerService.descriptor.getFeedback().setStartTime(10);
    typingTrainerService.descriptor.getFeedback().setEndTime(20);

    assertEquals(10, typingTrainerService.descriptor.getFeedback().getStartTime());
    assertEquals(20, typingTrainerService.descriptor.getFeedback().getEndTime());

    typingTrainerService.descriptor.getFeedback().calculateTime();
    assertEquals(10 / 1000000000.0, typingTrainerService.descriptor.getFeedback().getTime());

    String[] typedWords = {"Die", "3", "Lege"};

    typingTrainerService.descriptor.getFeedback().calculateWordsPerMinute(typedWords, practiceText.getText());
    assertEquals(2.147483647E9,typingTrainerService.descriptor.getFeedback().getWordsPerMinute()); //2,1 -> in GUI wird es gecuttet.
  }

  @Test
  @DisplayName("Tests if userinput get recognized")
  void testUserInput() throws IOException {
    int currentIndex = typingTrainerService.descriptor.getPracticeText().getCurrentWordIndex();
    typingTrainerService.userInput();
    assertEquals(currentIndex+1, typingTrainerService.descriptor.getPracticeText().getCurrentWordIndex());
  }

  @Test
  @DisplayName("Checks time calculation with countdown")
  void testCountdown() throws InterruptedException {
    typingTrainerService.countdown(11);
    typingTrainerService.countdown(66);

    typingTrainerService.descriptor.getFeedback().calculateTime();

    assertTrue(typingTrainerService.descriptor.getFeedback().getTime() < .1);
  }

  @Test
  @DisplayName("Checks if setter for TypedWords")
  void testSetTypedWords() {
    String[] newTypedWords = {"asd", "dar"};
    typingTrainerService.descriptor.setTypedWords(newTypedWords);
    assertTrue(typingTrainerService.descriptor.getTypedWords().equals(newTypedWords));
  }

  @Test
  @DisplayName("Checks get and set for AudioWrongWord in TypingTrainerDescriptor")
  void testSetGetAudioWrongWord() {
    File audioFile = new File("javafx/src/main/resources/typingTrainerFiles/8BIT RETRO Beep.mp3");
    typingTrainerService.descriptor.setAudioWrongWord(audioFile);
    assertEquals(audioFile, typingTrainerService.descriptor.getAudioWrongWord());
  }

  @Test
  @DisplayName("Checks setter feedback in TypingTrainerDescriptor")
  void testSetFeedbackDescriptor()
  {
    feedback = new Feedback(12,31);
    typingTrainerService.descriptor.setFeedback(feedback);
    assertEquals(feedback, typingTrainerService.descriptor.getFeedback());
  }

  @Test
  @DisplayName("Checks setter practiceText in TypingTrainerDescriptor")
  void testSetPracticeTextDescriptor(){
    typingTrainerService.descriptor.setPracticeText(practiceText);
    assertEquals(practiceText, typingTrainerService.descriptor.getPracticeText());
  }

  //endregion
  //region Feedback

  @Test
  @DisplayName("Tests Feedback Constructor and getTime() & getWPM()")
  void testFeedbackConstructor()
  {
    feedback = new Feedback(14.23, 3);

    assertEquals(14.23, feedback.getTime());
    assertEquals(3, feedback.getWordsPerMinute());
  }

  @Test
  @DisplayName("Test setWordsPerMinute()")
  void testFeedbackSetWPM()
  {
    feedback.setWordsPerMinute(54);
    assertTrue(54 == feedback.getWordsPerMinute());
  }

  @Test
  @DisplayName("Tests set & get for time in Feedback")
  void testFeedbackSetGetTime()
  {
    feedback.setTime(54.323);
    assertEquals(54.323, feedback.getTime());
  }

  @Test
  @DisplayName("Tests set & get for avgWordLength in Feedback")
  void testFeedbackSetGetAvarageWordLength()
  {
    feedback.setAvgWordLength(9);
    assertEquals(9, feedback.getAvgWordLength());
  }

  @Test
  @DisplayName("Tests set & get for counterRightWords")
  void testFeedbackSetGetCounterRightWords()
  {
    feedback.setCounterRightWords(99);
    assertEquals(99, feedback.getCounterRightWords());
  }

  @Test
  @DisplayName("Tests increasing counterRightWords")
  void testFeedbackIncreaseCounterRightWords()
  {
    feedback.setCounterRightWords(0);
    feedback.increaseCounterRightWords();
    assertEquals(1, feedback.getCounterRightWords());
  }

  @Test
  @DisplayName("Tests set & get startTime")
  void testFeedbackSetGetStartTime()
  {
    feedback.setStartTime(39.29);
    assertEquals(39.29, feedback.getStartTime());
  }

  @Test
  @DisplayName("Tests set & get endTime")
  void testFeedbackSetGetEndTime()
  {
    feedback.setEndTime(44.21);
    assertEquals(44.21, feedback.getEndTime());
  }

  //endregion
  //region PracticeText

  @Test
  @DisplayName("PracticeText Constructor")
  void testPracticeTextConstructor()
  {
    String[] text = {"text", "kinda", "sus"};
    practiceText = new PracticeText(text);

    assertEquals(text[0],practiceText.getWordAtIndex(0));
    assertEquals(text[1],practiceText.getWordAtIndex(1));
    assertEquals(text[2],practiceText.getWordAtIndex(2));
  }

  @Test
  @DisplayName("PracticeText text set & get")
  void testPracticeTextSetGetText()
  {
    String[] text = {"text", "kinda", "sus"};
    practiceText.setText(text);
    assertEquals(text, practiceText.getText());
  }

  @Test
  @DisplayName("PracticeText currentWordIndex set & get")
  void testPracticeTextSetGetCurrentWordIndex()
  {
    practiceText.setCurrentWordIndex(42);
    assertEquals(42,practiceText.getCurrentWordIndex());
  }

  @Test
  @DisplayName("PracticeText increaseCurrentWordIndex")
  void testPracticeTextIncreaseCurrentWordIndex()
  {
    practiceText.setCurrentWordIndex(0);
    practiceText.increaseCurrentWordIndex();
    assertEquals(1,practiceText.getCurrentWordIndex());
  }

  @Test
  @DisplayName("PracticeText getWordAtIndex")
  void testPracticeTextGetWordAtIndex()
  {
    assertEquals("Die", practiceText.getWordAtIndex(0));
  }

  //endregion
  //region SaveData

  @Test
  @DisplayName("SaveData Constructor & getters")
  void testSaveDataConstructorAndGetter()
  {
    SaveData data = new SaveData("konbanwa", "132", "5");

    assertTrue(data.getText().equals("konbanwa"));
    assertTrue(data.getTime().equals("132"));
    assertTrue(data.getWpm().equals("5"));
  }

  @Test
  @DisplayName("SaveData test setter methods")
  void testSaveDataSetter()
  {
    SaveData data = new SaveData("konbanwa", "132", "5");

    data.setText("mmm");
    data.setTime("222");
    data.setWpm("6");

    assertTrue(data.getText().equals("mmm"));
    assertTrue(data.getTime().equals("222"));
    assertTrue(data.getWpm().equals("6"));
  }

  //endregion

  @Test
  @DisplayName("FileReader test 2")
  void testFileReader2() throws FileNotFoundException {
    fileReader = new FileReader();
    String expectedFileContent = "Die 3 Legende von Momotaro, dem Pfirsichjungen, kennt in Japan jedes Kind.\n";
    assertEquals(expectedFileContent, fileReader.fileContent);
  }

  //region SaveLoad

  @Test
  @DisplayName("SaveLoad save() and load()")
  void testSaveLoad() throws IOException {
    String path = "highscores.txt";
    SaveLoad saveLoad = new SaveLoad();

    String preloadedData = saveLoad.load(path);

    saveLoad.save(path);

    String loadedData = saveLoad.load(path);

    String expected = preloadedData.concat("selectedtext 1.54 2 ");

    System.out.println(expected);
    System.out.println(loadedData);

    assertTrue(expected.equals(loadedData));
  }

  @Test
  @DisplayName("Other save method from SaveLoad")
  void testSaveLoadSaveWithArgs() throws IOException {
    String path = "highscores.txt";
    SaveLoad saveLoad = new SaveLoad();
    saveLoad.path = path;

    saveLoad.loadPath = path;
    String preloadedData = saveLoad.load();

    saveLoad.save("selectedtext", "1.54", "2");

    String loadedData = saveLoad.load();

    String expected = preloadedData.concat("selectedtext 1.54 2 ");

    System.out.println(expected);
    System.out.println(loadedData);

    assertTrue(expected.equals(loadedData));
  }

  //endregion
}
