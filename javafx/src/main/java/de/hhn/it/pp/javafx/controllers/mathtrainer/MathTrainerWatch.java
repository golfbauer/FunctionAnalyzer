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

    private BiKrMathTrainer mt;
    private MathTrainerController mtc;

    private Timeline timeline;
    private int STARTTIME = 5;
    private int timeSeconds = 0;

    public MathTrainerWatch(BiKrMathTrainer mt, MathTrainerController mtc) {
        this.mt = mt;
        this.mtc = mtc;
    }

    public int getSeconds() {
        return timeSeconds;
    }

    public void runWatch() {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds = STARTTIME;
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
