package de.hhn.it.pp.components.learningCards;

public class LearningProgress {

    // number of right answered questions
    int right = 0;
    // number of wrong answered questions
    int wrong = 0;

    // Method to increase right by 1
    public void updateRight() {
        right++;
    }

    // Method to increase wrong by 1
    public void updateWrong() {
        wrong++;
    }

    @Override
    public String toString() {
        return "right answered: " + right + ",   wrong answered: " + wrong;
    }

    // Method to reset right and wrong
    public void reset() {
        right = 0;
        wrong = 0;
    }

}
