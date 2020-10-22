import java.util.Scanner;

public class SessionManager {

	LearningProgress progress = new LearningProgress();
	Cardset cardSet = null;
	int cardIndex = 0;
	Scanner scanner = new Scanner(System.in);

	// Method to start learning session
	public void startLearningSession(Cardset cardSet) {
		this.cardSet = cardSet;
		while (cardSet.cardset.size() > cardIndex) {
			askQuestion(cardSet.getCardfromSet(cardIndex));
			cardIndex++;			
			if(stopLearningSession()) {
				break;
			}
		}
		cardIndex = 0;
		System.out.println("Result: " + progress.toString());
		System.out.println("******************************");
		progress.reset();

	}

	// Method to repeat unseen and unsolved Cards
	public void repeatUnseenAndUnsolvedCards(Cardset cardSet) {
		this.cardSet = cardSet;
		while (cardSet.cardset.size() > cardIndex) {
			Card card = cardSet.getCardfromSet(cardIndex);
			if (! card.seen ||
					! card.solved) {
				askQuestion(cardSet.getCardfromSet(cardIndex));
				cardIndex++;
				if(stopLearningSession()) {
					break;
				}
			} 
		}
		cardIndex = 0;
		System.out.println("Result: " + progress.toString());
		System.out.println("******************************");
		progress.reset();
	}
	
	// Method to show and mark the question
	public void askQuestion(Card card) {
		System.out.println(card.getText());
		card.cardSeen();
		System.out.println("If it is solved correctly, enter 1.\nOtherwise enter something else:");
		String input = scanner.nextLine();
		if (input.equals("1")) {
			card.cardSolved();
			progress.updateRight();
		} else {
			card.cardUnsolved();
			progress.updateWrong();
		}
	}
	
	// Method to stop learning session
	public boolean stopLearningSession() {
		System.out.println("If you want to quit, enter 'q'. If you want to see next Question, enter something else:");
		String input = scanner.nextLine();
		if (input.equals("q")) {
			this.cardSet = null;
			cardIndex = 0;
			return true;
		}
		return false;
	}
}
