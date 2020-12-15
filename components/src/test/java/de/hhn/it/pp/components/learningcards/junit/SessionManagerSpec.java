package de.hhn.it.pp.components.learningcards.junit;

import de.hhn.it.pp.components.learningcards.Cardset;
import de.hhn.it.pp.components.learningcards.SessionManager;
import de.hhn.it.pp.components.learningcards.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Map;

@ExtendWith(LearningCardsParameterResolver.class)
public class SessionManagerSpec {

    SessionManager sessionManager;
    // has 5 Cards
    Cardset capitalsCardset;




    @BeforeEach
    void setup(Map<String, Cardset> cardsets){
        capitalsCardset = cardsets.get("Capitals");
        sessionManager = new SessionManager();
        sessionManager.startLearningSession(capitalsCardset, new Status[]{
                Status.SOLVED, Status.UNSOLVED, Status.UNSEEN
        });
    }

    @Test
    void answerFirstCardRight(){
        sessionManager.answerRight();
        assertEquals(Status.SOLVED, capitalsCardset.getCards().get(0).getStatus());
    }

    @Test
    void answerFirstCardWrong(){
        sessionManager.answerWrong();
        assertEquals(Status.UNSOLVED, capitalsCardset.getCards().get(0).getStatus());
    }

    @Test
    void answerSecondCardRight(){
        sessionManager.getNextCard();
        sessionManager.answerRight();
        assertEquals(Status.SOLVED, capitalsCardset.getCards().get(1).getStatus());
    }

    @Test
    void answerSecondCardWrong(){
        sessionManager.getNextCard();
        sessionManager.getPreviousCard();
        sessionManager.getNextCard();
        sessionManager.answerWrong();
        assertEquals(Status.UNSOLVED, capitalsCardset.getCards().get(1).getStatus());
    }

    @Test
    void stopSessionAfterAnsweredFirstTwoCardsRight(){
        sessionManager.answerRight();
        sessionManager.getNextCard();
        sessionManager.answerRight();
        int[] result = sessionManager.stopLearningSession();
        int solved = result[0];
        int unsolved = result[1];
        int unseen = result[2];
        assertEquals(2, solved);
        assertEquals(0, unsolved);
        assertEquals(capitalsCardset.getNumberOfCard() - 2, unseen);
    }
}
