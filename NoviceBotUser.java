package SE116PROJECT;

public class NoviceBotUser extends AbstractUser {

	NoviceBotUser(String name) {
		super(name);
	}

	
	public Card evaluateBoard() {
		Card card= new Card();
		return card;
	}
}
