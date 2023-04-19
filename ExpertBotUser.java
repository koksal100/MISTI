package SE116PROJECT;

public class ExpertBotUser extends AbstractUser {

	ExpertBotUser(String name) {
		super(name);
	}
	ExpertBotUser( ) {
	}
	public Card evaluateBoard() {
		Card card= new Card();
		return card;
	}
}
