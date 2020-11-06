package de.hhn.it.pp.components.learningCards;


class Card {
    // Scanner for the Console inputs
   
    // Card TextA (for  questions )
    String textQ;
    // Card textB (for answers)
    String textA;
    // identifier for Card
    int idC;
    // Card Headline
    String headline;
    // marker if card is solved
    boolean solved = false;
    // marker if card has been seen
    boolean seen = false;

    // Card Constructor, sets Headline, TextQ and TextA 
    Card(String headline, String textQ, String textA) {
        setHeadline(headline);
        setTextQ(textQ);
        setTextA(textA);
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
        info = "Headline: "+ getHeadline() "\n Question: " + getTextQ()+ "\n Answer: "+ getTextA();
        return info;
    }

    // sets seen status to true
    void cardSeen() {
        seen = true;
    }

    // sets solved status to true
    void cardSolved() {
        solved = true;
    }

    // sets solved status to false (used in case a Card was flagged by mistake (or something else))
    void cardUnsolved() {
        solved = false;
    }
}


