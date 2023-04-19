package SE116PROJECT;

import java.nio.file.Paths;
import java.util.Scanner;

public class Card {
	private int value;
	private String suit;
	private String cardface;
	private String cardName;

	Card(String suit, String cardface) {
		setCardface(cardface);
		setSuit(suit);
		setValue();

	}

	public String getCardName() {
		return suit + " " + cardface;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {

		this.suit = suit;
	}

	public String getCardface() {
		return cardface;
	}

	public void setCardface(String cardface) {
		this.cardface = cardface;
	}

	public int getValue() {
		return value;
	}

	public void setValue() {
		Scanner reader = null;
		String[] informations = new String[2];
		boolean isAssigned = false;
		int i = 0;
		try {
			reader = new Scanner(Paths.get("points.txt"));
			while (reader.hasNextLine()) {
				informations = reader.nextLine().split(" ");
				if ((this.getSuit() + this.getCardface()).equals(informations[0])
						|| ("*" + this.getCardface()).equals(informations[0])
						|| (this.getSuit() + "*").equals(informations[0])) {
					this.value = Integer.parseInt(informations[1]);
					isAssigned = true;
					break;
				}

			}
			if (!isAssigned) {
				this.value = 1;
			}

		} catch (Exception e) {

		} finally {
			if (reader != null) {
				reader.close();
			}
		}

	}
}
