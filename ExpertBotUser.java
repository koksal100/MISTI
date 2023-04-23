package SE116PROJECT;

import java.util.ArrayList;

public class ExpertBotUser extends AbstractUser {
	private ArrayList<Card> allPlayedCards;
	private Card topCard;
	private int totalBoardPoint;
	private boolean isCardEnough = false;
	private Card nonPlayedCard;
	private boolean Cardfunc =false;

	ExpertBotUser(String name) {
		super(name);
	}

	ExpertBotUser() {
	}

	public Card findBestCardToPlay() {//NULL HATASINA DİKKAT !!!
		Card card = new Card();
		boolean isCardMatched = false;

		if (totalBoardPoint >= 0) {
			for (int i = 0; i < getCurrentCards().size(); i++) {
				if (getCurrentCards().get(i).getCardface().equals(topCard.getCardface()) && getCurrentCards().get(i).getValue() + totalBoardPoint >= 0) {
					isCardMatched = true;
					return getCurrentCards().get(i);
				} else if (getCurrentCards().get(i).getCardface().equals("Vale") && getCurrentCards().get(i).getValue() + totalBoardPoint >= 0) {
					isCardMatched = true;
					return getCurrentCards().get(i);
				}
			}
			if (!isCardMatched) {
				card = playMostFrequentCard();
				if (isCardEnough == true && card.getValue() + totalBoardPoint >= 0) {
					return card;
				} else {
					int indexOfMinValue = 0;
					int lowestValueCard = getCurrentCards().get(0).getValue();
					for (int i = 1; i < getCurrentCards().size(); i++) {
						if (getCurrentCards().get(i).getValue() < lowestValueCard) {
							lowestValueCard = getCurrentCards().get(i).getValue();
							indexOfMinValue = i;
						}
					}
					return getCurrentCards().get(indexOfMinValue);
				}
			}
		} else {
			for (int i = 0; i < getCurrentCards().size(); i++) {
				if (getCurrentCards().get(i).getCardface().equals(topCard.getCardface()) && getCurrentCards().get(i).getValue() + totalBoardPoint >= 0) {
					isCardMatched = true;
					return getCurrentCards().get(i);
				} else if (getCurrentCards().get(i).getCardface().equals("Vale") && getCurrentCards().get(i).getValue() + totalBoardPoint >= 0) {
					isCardMatched = true;
					return getCurrentCards().get(i);
				} else if (getCurrentCards().get(i).getCardface().equals(topCard.getCardface()) && getCurrentCards().get(i).getValue() + totalBoardPoint < 0) {
					Cardfunc = true;
					isCardMatched = true;
					nonPlayedCard = getCurrentCards().get(i);
				}
			}
			if (isCardMatched == true && Cardfunc == true) {
              card=isCardMatched(isCardMatched,card);
			  if(card !=nonPlayedCard){
				  return card;
			  }else{
				  card=isCardMatched(isCardMatched,card);
			  }
			}
		}
		   return card;
	}

	public void findTopCard(ArrayList<Card> board) {
		if (board.size() > 0) {
			this.topCard = board.get(board.size() - 1);
		} else {
			this.topCard = null;
		}
	}

	public void keepTrackOfAllPlayedCards(Card playedCard) {
		allPlayedCards.add(playedCard);
	}

	public void calculateTotalBoardPoint(ArrayList<Card> board) { //null hatası gelebilir
		if (topCard == null) {
			totalBoardPoint = 0;
		}

		for (int i = 0; i < board.size(); i++) {
			this.totalBoardPoint += board.get(i).getValue();
		}
	}
	public Card playMostFrequentCard() {
		Card mostFrequentCard = getCurrentCards().get(0);
		int maxPlayedNumber = 0;

		// Loop over the user's current cards and count their occurrences

		for (Card card : getCurrentCards()) {
			int PlayedNumber = 0;
			for (Card playedCard : allPlayedCards) {
				if (playedCard.getCardface().equals(card.getCardface())) {
					PlayedNumber++;
				}
			}
			if (PlayedNumber > maxPlayedNumber && PlayedNumber>1) {
				isCardEnough=true;
				mostFrequentCard = card;
				maxPlayedNumber = PlayedNumber;
			}
		}

		// Return the most frequent card, or null if no card was found
		return mostFrequentCard;
	}
	public Card leastPlayedCard(){
		Card leastPlayedCard = getCurrentCards().get(0);
		int leastPlayedNumber =0;
		for (Card card : getCurrentCards()) {
			int PlayedNumber = 0;
			for (Card playedCard : allPlayedCards) {
				if (playedCard.getCardface().equals(card.getCardface())) {
					PlayedNumber++;
				}
			}
			if (PlayedNumber < leastPlayedNumber && PlayedNumber<3) {
				isCardEnough=true;
				leastPlayedCard = card;
				leastPlayedNumber = PlayedNumber;
			}
		}
		return leastPlayedCard;
	}
  public Card isCardMatched(boolean isCardMatched,Card card) {
	  if (isCardMatched == false) {
		  card = leastPlayedCard();
		  if (isCardEnough == true && card.getValue() + totalBoardPoint < 0) {
			  return card;
		  } else {
			  int indexOfMinValue = 0;
			  int lowestValueCard = getCurrentCards().get(0).getValue();
			  for (int i = 1; i < getCurrentCards().size(); i++) {
				  if (getCurrentCards().get(i).getValue() < lowestValueCard) {
					  lowestValueCard = getCurrentCards().get(i).getValue();
					  indexOfMinValue = i;
				  }
			  }
			  return getCurrentCards().get(indexOfMinValue);
		  }
	  }
	  return card;
  }
}
