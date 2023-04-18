package SE116PROJECT;

public class RegularBotUser extends AbstractUser {
	RegularBotUser(String name) {
		super(name);
	}

	
	public Card evaluateBoard() {
		Card card= new Card("nur","nütnüt");
		return card;
	}
}
