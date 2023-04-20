package SE116PROJECT;

import java.util.ArrayList;

public class ExpertBotUser extends AbstractUser {
	private ArrayList<Card> allPlayedCards;
	private Card topCard;
	private int totalBoardPoint;
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
	public void calculateTotalBoardPoint(ArrayList<Card> board){ //null hatası gelebilir
		if(topCard==null){
			totalBoardPoint=0;
		}

		for(int i=0;i<board.size();i++){
			this.totalBoardPoint+=board.get(i).getValue();
		}
	}
}
