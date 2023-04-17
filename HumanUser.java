package SE116PROJECT;

public class HumanUser extends AbstractUser {

	HumanUser(String name) {
		super(name);
	}

	public Card evaluateBoard() {
		Card card= new Card();
		return card;
	}
}
