package SE116PROJECT;

import java.util.ArrayList;

public class ExpertBotUser extends AbstractUser {
	private ArrayList<Card> allPlayedCards;
	private Card topCard;
	ExpertBotUser(String name) {
		super(name);
	}
	ExpertBotUser( ) {
	}
	public Card findBestCardToPlay() {//NULL HATASINA DİKKAT !!!
		Card card= new Card();
		return card;
	}
	public void findTopCard(ArrayList<Card> board){
		if(board.size()>0) {
			this.topCard = board.get(board.size() - 1);
		}else{
			this.topCard=null;
		}
	}

	public void keepTrackOfAllPlayedCards(Card playedCard){
		allPlayedCards.add(playedCard);
	}
}
