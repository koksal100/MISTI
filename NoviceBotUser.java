package SE116PROJECT;

public class NoviceBotUser extends AbstractUser {

	NoviceBotUser(String name) {
		super(name);
	}
	NoviceBotUser() {

	}
	
	public Card findBestCardToPlay() {
		Card card= new Card();
		return card;
	}
}
