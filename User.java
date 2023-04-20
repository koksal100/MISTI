package SE116PROJECT;

import java.util.ArrayList;

public interface User {

	public void collectCards(ArrayList<Card> boardCards);

	public void showCurrentCards();

	public void showCollectedCards();

	public void playCardTo(ArrayList<Card> cards);

	public Card findBestCardToPlay( );

	
}