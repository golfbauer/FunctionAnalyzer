package de.hhn.it.pp.components.learningcards.junit;

import de.hhn.it.pp.components.learningcards.LearningProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LearningProgressSpec {
    LearningProgress learningProgress ;

    @BeforeEach
    void setup(){
        learningProgress = new LearningProgress();
    }

    @Test
    void updateRights(){
        learningProgress.updateRight();
        learningProgress.updateRight();
        assertEquals(2, learningProgress.getRight());
    }

    @Test
    void updateWrongs(){
        learningProgress.updateWrong();
        assertEquals(1, learningProgress.getWrong());
    }

    @Test
    void resetLearningProgress(){
        learningProgress.updateWrong();
        learningProgress.updateWrong();
        learningProgress.updateWrong();
        learningProgress.updateRight();
        learningProgress.updateRight();

        learningProgress.reset();

        assertEquals(0, learningProgress.getWrong());
        assertEquals(0, learningProgress.getRight());

    }
}
