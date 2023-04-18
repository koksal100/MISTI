package SE116PROJECT;

import java.util.ArrayList;

public abstract class AbstractUser implements User {
	// collectCards(), showCurrentCards(),showCollectedCards(),playCard() will be
	// implemented there, because
	// they are same for all user types
	// evaluateBoard() function will be implemented in subclasses.
	// this class also have name and score attributes and there are suitable setters
	// and getters methods for them
	// and one parameter constructor.

	private String name;
	private int score;
	private ArrayList<Card> currentCards;
	private ArrayList<Card> collectedCards;

	AbstractUser(String name) {
		setName(name);
		score = 0;
	}

	AbstractUser() {

	}

	public void addToCurrentCards(Card card) {

	}

	public ArrayList<Card> getcollectedCards() {
		return collectedCards;
	}

	public void addTocollectedCards(ArrayList<Card> cards) {

	}

	public ArrayList<Card> getCurrentCards() {
		return currentCards;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final void collectCards() {

	}

	public final void showCurrentCards() {

	}

	public final void showCollectedCards() {

	}

	public final void playCard() {

	}

}
