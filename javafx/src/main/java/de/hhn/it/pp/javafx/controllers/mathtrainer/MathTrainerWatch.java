package de.hhn.it.pp.javafx.controllers.mathtrainer;

import de.hhn.it.pp.components.mathtrainer.BiKrMathTrainer;
import de.hhn.it.pp.javafx.controllers.MathTrainerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class MathTrainerWatch {
  private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(MathTrainerController.class);

  private final BiKrMathTrainer mt;
  private final MathTrainerController mtc;

  private Timeline timeline;
  private int timeSeconds = 0;

  public MathTrainerWatch(BiKrMathTrainer mt, MathTrainerController mtc) {
    this.mt = mt;
    this.mtc = mtc;
  }

  public int getSeconds() {
    return timeSeconds;
  }

  /**
  * method runs a counter from 5 to 0 and sets the field timeSeconds to an integer.
  */
  public void runWatch() {
    if (timeline != null) {
      timeline.stop();
    }
    timeSeconds = 5;
    mt.setTimeIsUp(false);
    // update timerLabel
    timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.getKeyFrames().add(
        new KeyFrame(Duration.seconds(1),
        new EventHandler() {
          @Override
          public void handle(Event event) {
            timeSeconds--;
            if (timeSeconds <= 0) {
              logger.info("TIME IS UP!");
              mt.setTimeIsUp(true);
              mtc.timeIsUp();
              timeline.stop();
            }
          }
        }));
    timeline.playFromStart();
  }
}
