package SE116PROJECT;

import java.util.ArrayList;

public class ExpertBotUser extends AbstractUser {
	private ArrayList<Card> allPlayedCards=new ArrayList<>();
	private Card topCard;
	private int totalBoardPoint;
	private boolean isCardEnough = false;
	private Card nonPlayCard;


	ExpertBotUser(String name) {
		super(name);
	}

	ExpertBotUser() {
		this.setName("ExpertBot");
	}

	public Card findBestCardToPlay() {//NULL HATASINA DİKKAT !!!
		int valeIndex = 0;
		int matchedCardIndex = 0;
		boolean isCardAssigned = false;
		boolean isMatchedCardFaceFound = false;
		boolean isValeFound = false;
		boolean isValeUsefull = false;
		boolean isMatchedCardUsefull = false;
		//ARRAYLİSTLER
		ArrayList<Card> otherOptions = new ArrayList<>();
		ArrayList<String> cardFaces = new ArrayList<>();

		for (int i = 0; i < getCurrentCards().size(); i++) {
			cardFaces.add(getCurrentCards().get(i).getCardface());
		}

		if (getCurrentCards().contains("VALE")) {
			isValeFound = true;
			valeIndex = getCurrentCards().indexOf("VALE");
		}

		if (cardFaces.contains(topCard.getCardface())) {
			isMatchedCardFaceFound = true;
			matchedCardIndex = cardFaces.indexOf(topCard.getCardface());
		}
		if (isMatchedCardFaceFound) {
			if (totalBoardPoint + getCurrentCards().get(matchedCardIndex).getValue() > 0) {
				isMatchedCardUsefull = true;
			}
		}

		if (isValeFound) {
			if (totalBoardPoint + getCurrentCards().get(valeIndex).getValue() > 0) {
				isValeUsefull = true;
			}
		}
		while (isCardAssigned == false) {
			if (isMatchedCardUsefull) {
				isCardAssigned = true;
				return getCurrentCards().get(matchedCardIndex);
			} else if (isValeUsefull == true) {
				isCardAssigned = true;
				return getCurrentCards().get(valeIndex);
			}
			if (!isValeUsefull && !isMatchedCardUsefull) {
				boolean isThereAnotherOption = false;
				//checks for a card except vale and top card
				for (Card cards : getCurrentCards()) {
					if (!cards.equals("VALE") || !cards.equals(topCard.getCardface())) {
						isThereAnotherOption = true;
						break;
					}
				}
				if (isThereAnotherOption) {
					//list cards except top card and vale
					for (int i = 0; i < getCurrentCards().size(); i++) {
						if (!getCurrentCards().get(i).equals("VALE") && !getCurrentCards().get(i).equals(topCard.getCardface())) {
							otherOptions.add(getCurrentCards().get(i));
						}
					}
					if (totalBoardPoint >= 0) {
						Card mostFrequentCard = playMostFrequentCard(otherOptions);
						if (isCardEnough) {
							isCardAssigned = true;
							return mostFrequentCard;
						} else {
							int min = otherOptions.get(0).getValue();
							int minValueIndex = 0;

							for (int i = 0; i < otherOptions.size(); i++) {
								if (otherOptions.get(i).getValue() < min) {
									min = otherOptions.get(i).getValue();
									minValueIndex = i;
									isCardAssigned = true;
								}

							}
							return otherOptions.get(minValueIndex);
						}
					} else {
						Card leastPlayedCard = leastPlayedCard(otherOptions);
						if (isCardEnough) {
							isCardAssigned = true;
							return leastPlayedCard;
						}
					}
				} else {
					isCardAssigned = true;
					if(cardFaces.contains(topCard.getCardface())){
						return getCurrentCards().get(matchedCardIndex);
					}else {
						return getCurrentCards().get(valeIndex);
					}
				}
			}
		}
		return null;
	}

	public void findTopCard(ArrayList<Card> board) {
		if (board.size() > 0) {
			this.topCard = board.get(board.size() - 1);
		} else {
			this.topCard = null;
		}
	}

	public void keepTrackOfAllPlayedCards() {
		allPlayedCards.add(this.topCard);
	}

	public void calculateTotalBoardPoint(ArrayList<Card> board) { //null hatası gelebilir
		if (topCard == null) {
			totalBoardPoint = 0;
		}

		for (int i = 0; i < board.size(); i++) {
			this.totalBoardPoint += board.get(i).getValue();
		}
	}

	public Card playMostFrequentCard(ArrayList<Card> otherOptions) {
		Card mostFrequentCard = otherOptions.get(0);
		int maxPlayedNumber = 0;
		isCardEnough = false;
		// Loop over the user's current cards and count their occurrences

		for (Card card : otherOptions) {
			int PlayedNumber = 0;
			for (Card playedCard : allPlayedCards) {
				if (playedCard.getCardface().equals(card.getCardface())) {
					PlayedNumber++;
				}
			}
			if (PlayedNumber > maxPlayedNumber && PlayedNumber > 1) {
				isCardEnough = true;
				mostFrequentCard = card;
				maxPlayedNumber = PlayedNumber;
			}
		}
		// Return the most frequent card, or null if no card was found
		return mostFrequentCard;
	}

	public Card leastPlayedCard(ArrayList<Card> otherOptions) {
		Card leastPlayedCard = otherOptions.get(0);
		int leastPlayedNumber = 0;
		for (Card card : otherOptions) {
			int PlayedNumber = 0;
			for (Card playedCard : allPlayedCards) {
				if (playedCard.getCardface().equals(card.getCardface())) {
					PlayedNumber++;
				}
			}
			if (PlayedNumber < leastPlayedNumber && PlayedNumber < 3) {
				isCardEnough = true;
				leastPlayedCard = card;
				leastPlayedNumber = PlayedNumber;
			}
		}
		return leastPlayedCard;
	}
}