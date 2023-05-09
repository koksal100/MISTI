package SE116PROJECT;

import java.util.ArrayList;

public abstract class AbstractUser implements User {
    // collectCards(), showCurrentCards(),showCollectedCards(),playCard() will be
    // implemented there, because
    // they are same for all user types
    // findBestCardToPlay() function will be implemented in subclasses.
    // this class also have name and score attributes and there are suitable setters
    // and getters methods for them
    // and one parameter constructor.

    private String name;
    private int tuna;
    private int score;
    private ArrayList<Card> currentCards = new ArrayList<>();
    private ArrayList<Card> collectedCards = new ArrayList<>();

    AbstractUser(String name) {
        setName(name);
        score = 0;
    }

    AbstractUser() {

    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public final void addToCurrentCards(Card card) {
        this.getCurrentCards().add(card);
    }

    public final ArrayList<Card> getCurrentCards() {
        return currentCards;
    }

    public final void collectCards(ArrayList<Card> boardCards) {
        this.getcollectedCards().addAll(boardCards);
        boardCards.clear();
    }

    public final ArrayList<Card> getcollectedCards() {
        return collectedCards;
    }

    public final void showCurrentCards() {
        System.out.print(getName() + "' s cards: ");
        for (int i = 0; i < this.getCurrentCards().size(); i++) {
            System.out.print( "  ▊" + this.getCurrentCards().get(i).getCardName()+ "  ");
        }



    }

    public final void showCollectedCards() {
        System.out.print(getName() + "' s collected cards: ");
        for (int i = 0; i < this.getcollectedCards().size(); i++) {
            System.out.print( "  ▊" + this.getcollectedCards().get(i).getCardName() + "  ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void calculateCollectedCardsScore() {
        for (int i = 0; i < collectedCards.size(); i++) {
            this.score += collectedCards.get(i).getValue();
        }

    }

    public final void playCardTo(ArrayList<Card> boardCards) {
        Card card = findBestCardToPlay();
        boardCards.add(card);
        this.getCurrentCards().remove(card);
    }

    public final void raceWithOthers(ArrayList<AbstractUser> topTenUsers) {
        int size = topTenUsers.size();

        boolean isAssigned = false;

        for (int i = 0; i < size; i++) {

            if (topTenUsers.get(i).getScore() < this.getScore()) {

                topTenUsers.add(i, this);
                isAssigned = true;
                break;
            }
        }

        if (topTenUsers.size() > 10) {

            topTenUsers.remove(topTenUsers.size() - 1);
        } else if (topTenUsers.size() < 10 && !isAssigned) {

            topTenUsers.add(this);
        }
    }

}

