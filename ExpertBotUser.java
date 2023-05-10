package SE116PROJECT;

import java.util.ArrayList;

public class ExpertBotUser extends AbstractUser {
    private static int numberOfProducedPlayer = 1;
    private ArrayList<Card> allPlayedCards = new ArrayList<>();
    private Card topCard;
    private int totalBoardPoint;
    private boolean isCardEnough = false;
    private Card nonPlayCard;


    ExpertBotUser(String name) {
        super(name);
    }

    ExpertBotUser() {

        this.setName("ExpertBot" + " " + numberOfProducedPlayer++);
    }

    public ArrayList<Card> getAllPlayedCards() {
        return allPlayedCards;
    }

    public Card findBestCardToPlay() {//The function that returns the card the computer needs to play.
        int valeIndex = 0;
        int matchedCardIndex = 0;
        boolean isCardAssigned = false;
        boolean isMatchedCardFaceFound = false;////The value that checks whether we have a matching card with the card on the board.
        boolean isValeFound = false;//Are we have jack or not
        boolean isValeUsefull = false;//The value that becomes true if playing a Jack card yields positive points for us, and false if it does not.
        boolean isMatchedCardUsefull = false;//If playing a matching card results in negative points for our hand, this value becomes false.
        ArrayList<Card> otherOptions = new ArrayList<>();
        ArrayList<String> cardFaces = new ArrayList<>();

        for (int i = 0; i < getCurrentCards().size(); i++) {
            cardFaces.add(getCurrentCards().get(i).getCardface());
        }

        if (getCurrentCards().contains("J")) {//We are checking if we have a Jack card or not and updating the boolean value.
            isValeFound = true;
            valeIndex = getCurrentCards().indexOf("J");
        }

        if (topCard == null) {
            for (int i = 0; i < getCurrentCards().size(); i++) {
                if (!getCurrentCards().get(i).equals("J")) {//We use it to avoid playing a Jack card unnecessarily when there is no card on the board.
                    otherOptions.add(getCurrentCards().get(i));
                }
            }
            Card card = playMostFrequentCard(otherOptions);//We are discarding the card with a higher play count from the otherOptions array.
            return card;
        }

        if (cardFaces.contains(topCard.getCardface())) { //We are checking if there is a matching card or not.
            isMatchedCardFaceFound = true;
            matchedCardIndex = cardFaces.indexOf(topCard.getCardface());
        }
        if (isMatchedCardFaceFound) { //We are checking if we receive a positive score when we take the matching board cards.
            if (totalBoardPoint + getCurrentCards().get(matchedCardIndex).getValue() > 0) {
                isMatchedCardUsefull = true;
            }
        }

        if (isValeFound) {
            if (totalBoardPoint + getCurrentCards().get(valeIndex).getValue() > 0) {//We are checking if we receive a positive score when we take the board cards.
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
                    if (!cards.equals("J") && !cards.equals(topCard.getCardface())) {
                        isThereAnotherOption = true;
                        break;
                    }
                }
                if (isThereAnotherOption) {
                    //list cards except top card and vale
                    for (int i = 0; i < getCurrentCards().size(); i++) {
                        if (!getCurrentCards().get(i).equals("J") && !getCurrentCards().get(i).equals(topCard.getCardface())) {
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

                        return leastPlayedCard;
                        // if (isCardEnough) {
                        //   isCardAssigned = true;

                        //}
                    }
                } else {
                    isCardAssigned = true;
                    if (cardFaces.contains(topCard.getCardface())) {
                        return getCurrentCards().get(matchedCardIndex);
                    } else {
                        return getCurrentCards().get(valeIndex);
                    }
                }
            }
        }
        return null;
    }

    public void findTopCard(ArrayList<Card> board) {//This function is used to find the top card on the board.
        if (board.size() > 0) {
            this.topCard = board.get(board.size() - 1);
        } else {
            this.topCard = null;
        }
    }

    public void keepTrackOfAllPlayedCards() {
        if (topCard != null && !allPlayedCards.contains(topCard)) {

            allPlayedCards.add(this.topCard);
        }
    }

    public void calculateTotalBoardPoint(ArrayList<Card> board) { //This function is used to calculate the total score of the cards in board.
        if (topCard == null) {
            totalBoardPoint = 0;
        }

        for (int i = 0; i < board.size(); i++) {
            this.totalBoardPoint += board.get(i).getValue();
        }
    }

    public Card playMostFrequentCard(ArrayList<Card> otherOptions) { // The function that allows us to play the most frequently played card from previous hands if our current cards do not match or if taking the cards on the board does not benefit us.
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


    public Card leastPlayedCard(ArrayList<Card> otherOptions) { //The function that allows us to play the least played card and decrease the opponent's score if the total score of the cards on the board is negative.
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