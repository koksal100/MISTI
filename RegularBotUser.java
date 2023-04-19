package SE116PROJECT;

public class RegularBotUser extends AbstractUser {
	RegularBotUser(String name) {
		super(name);
	}
	RegularBotUser() {

	}
	
	public Card evaluateBoard() {
		Card card= new Card();
		return card;
	}
}
