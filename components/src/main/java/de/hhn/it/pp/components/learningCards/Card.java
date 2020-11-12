package de.hhn.it.pp.components.learningCards;


class Card {


    Status status = Status.UNSEEN;
    // Card TextA (for  questions )
    String textQ;
    // Card textB (for answers)
    String textA;
    // identifier for Card
    int id;
    static int idCounter = 0;
    // Card Headline
    String headline;


    // Card Constructor, sets Headline, TextQ and TextA 
    Card(String headline, String textQ, String textA) {
        setHeadline(headline);
        setTextQ(textQ);
        setTextA(textA);
        id = ++idCounter;
    }

    // Method to set Headline (console input)
    void setHeadline(String headline) {

        this.headline = headline;
    }

    // not intended for demo version (empty method)
    void addPicture() {
    }

    // Method to set Question text
    void setTextQ(String question) {
        textQ = question;
    }

    // Method to set Answer text
    void setTextA(String answer) {
        textA = answer;
    }

    // Method to change allready set Question Text 
    void editTextQ(String changedQ) {
        setTextQ(changedQ);
    }

    // Method to change allready set Answer Text
    void editTextA(String changedA) {

        setTextA(changedA);
    }

    int getId() {
        return id;
    }

    // returns Question text
    String getTextQ() {
        return textQ;
    }

    // returns Answer text
    String getTextA() {
        return textA;
    }

    // returns Headline
    String getHeadline() {
        return headline;
    }

    // Combines all Strings in one String and returns it.
    String getCardinfo() {
        String info;
        info = "Headline: " + getHeadline() + "\n Question: " + getTextQ() + "\n Answer: " + getTextA();
        return info;
    }

    Status getStatus() {
        return status;
    }

    void setStatus(Status newStatus) {
        status = newStatus;
    }

}


