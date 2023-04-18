package SE116PROJECT;

public class ExpertBotUser extends AbstractUser {

	ExpertBotUser(String name) {
		super(name);
	}

	public Card evaluateBoard() {
		Card card= new Card("nur","nütnüt");
		return card;
	}
}
