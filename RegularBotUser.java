package SE116PROJECT;

import java.util.ArrayList;

public class RegularBotUser extends AbstractUser {
    private Card topCard;
    private int totalBoardPoint = 0;

    RegularBotUser(String name) {
        super(name);
    }

    RegularBotUser() {

    }

    public Card findBestCardToPlay() { //NULL HATASINA DİKKAT !!!

        int valeIndex = 0;
        int matchedCardIndex = 0;
        boolean isCardAssigned = false;
        boolean isMatchedCardFaceFound = false;
        boolean isValeFound = false;
        boolean isValeUsefull = false;
        boolean isMatchedCardUsefull = false;
        Card selectedCard;

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


        while (!isCardAssigned) {

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
                    ArrayList<Card> otherOptions = new ArrayList<>();


                    for (int i = 0; i < getCurrentCards().size(); i++) {
                        if (!getCurrentCards().get(i).equals("VALE") && !getCurrentCards().get(i).equals(topCard.getCardface())) {
                            otherOptions.add(getCurrentCards().get(i));
                        }
                    }
                    int min = otherOptions.get(0).getValue();

                    for (int i = 0; i < otherOptions.size(); i++) {
                        if (otherOptions.get(i).getValue() < min) {
                            min = otherOptions.get(i).getValue();
                            isCardAssigned = true;
                            return otherOptions.get(i);
                        }
                    }

                } else {
                    isCardAssigned = true;
                    return getCurrentCards().get(0);

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

    public void calculateTotalBoardPoint(ArrayList<Card> board) { //null hatası gelebilir
        if (topCard == null) {
            totalBoardPoint = 0;
        } else {
            for (int i = 0; i < board.size(); i++) {
                this.totalBoardPoint += board.get(i).getValue();
            }


        }
    }
}