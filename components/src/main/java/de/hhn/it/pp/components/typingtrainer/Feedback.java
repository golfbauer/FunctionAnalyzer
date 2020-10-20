package de.hhn.it.pp.components.typingtrainer;

/***
 * @author Tobias Maraci, Robert Pistea
 */

public class Feedback{

    float time;
    float wordsPerMinute;

    public Feedback(float time, float wordsPerMinute)
    {
        this.time = time;
        this.wordsPerMinute = wordsPerMinute;
    }
}