package SE116PROJECT;
import java.util.Random;
import java.util.ArrayList;

public class RegularBotUser extends AbstractUser {
    private static int numberOfProducedPlayer=1;
    private static Random rd = new Random();
    private Card topCard;
    private int totalBoardPoint = 0;

    RegularBotUser(String name) {
        super(name);
    }

    RegularBotUser() {
        this.setName("RegularBot"+" "+numberOfProducedPlayer++);
    }

    public Card findBestCardToPlay() {

        int valeIndex = 0;
        int matchedCardIndex = 0;
        boolean isCardAssigned = false;
        boolean isMatchedCardFaceFound = false;//The value that checks whether we have a matching card with the card on the board.
        boolean isValeFound = false;//Are we have jack or not
        boolean isValeUsefull = false;//The value that becomes true if playing a Jack card yields positive points for us, and false if it does not.
        boolean isMatchedCardUsefull = false;//If playing a matching card results in negative points for our hand, this value becomes false.
        ArrayList<Card> otherOptions = new ArrayList<>();


        ArrayList<String> cardFaces = new ArrayList<>();


        for (int i = 0; i < getCurrentCards().size(); i++) {
            cardFaces.add(getCurrentCards().get(i).getCardface());
        }

        if (cardFaces.contains("VALE")) {
            isValeFound = true;
            valeIndex = getCurrentCards().indexOf("J");
        }
        if(topCard==null){
            for (int i = 0; i < getCurrentCards().size(); i++) {
                if (!getCurrentCards().get(i).equals("J") ) {
                    otherOptions.add(getCurrentCards().get(i));
                }
            }
            Card card = otherOptions.get(rd.nextInt(otherOptions.size()));
            return card;
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


        while (!isCardAssigned) { //The while loop continues until a card to play is selected.

            if (isMatchedCardUsefull) {
                isCardAssigned = true;
                return getCurrentCards().get(matchedCardIndex);
            } else if (isValeUsefull) {
                isCardAssigned = true;
                return getCurrentCards().get(valeIndex);
            }


            if (!isValeUsefull && !isMatchedCardUsefull) {
                boolean isThereAnotherOption = false;
                //checks for a card except vale and top card
                for (Card cards : getCurrentCards()) {
                    if (!cards.getCardface().equals("J") && !cards.getCardface().equals(topCard.getCardface())) {
                        isThereAnotherOption = true;
                        break;
                    }
                }

                if (isThereAnotherOption) {
                    //list cards except top card and vale


                    for (int i = 0; i < getCurrentCards().size(); i++) {
                        if (!getCurrentCards().get(i).getCardface().equals("J") && !getCurrentCards().get(i).getCardface().equals(topCard.getCardface())) {
                            otherOptions.add(getCurrentCards().get(i));
                        }
                    }
                    //expert bot

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
                    //expert bot
                } else { //We have to take a card from the middle because we only have J cards, and the top card is the same as the cards in our hand.
                    isCardAssigned = true;
                    return getCurrentCards().get(0);

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

    public void calculateTotalBoardPoint(ArrayList<Card> board) { //This function is used to calculate the total score of the cards in board.
        if (topCard == null) {
            totalBoardPoint = 0;
        } else {
            for (int i = 0; i < board.size(); i++) {
                this.totalBoardPoint += board.get(i).getValue();
            }


        }
    }
}