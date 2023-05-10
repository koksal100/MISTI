package SE116PROJECT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Deck {
    private ArrayList<Card> cards;



    public Deck() {
        this.cards=InitializeDeck();
    }




    private ArrayList<Card> InitializeDeck(){
        ArrayList<Card> cards = new ArrayList<>(52);

        String[] suits ={"♠", "♥", "♣", "♦"};
        String[] ranks = {"A","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }




        return cards ;
    }


    public void shuffleDeck(){
        Collections.shuffle(cards);
    }
    public void cutDeck(){

        Random rnd = new Random();
        int cut = rnd.nextInt(52);
        List<Card> firstHalf = cards.subList(0,cut);
        List<Card> secondHalf = cards.subList(cut,cards.size());
        ArrayList<Card> newCards = new ArrayList<>();
        newCards.addAll(secondHalf);
        newCards.addAll(firstHalf);
        cards = newCards;
    }

    public ArrayList<Card> getCards() {

        return cards;
    }

}