package SE116PROJECT;

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

	public Card() {

	}

	public String getCardName(){
		return suit+ " " +cardface;
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
		// GET VALUES FROM TXT FILE
	}
}
