package SE116PROJECT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Deck {
    private ArrayList<Card> cards;



    public Deck() {
        this.cards=InitializeDeck();
    }

public void printDeck(){ //CONTROL
    for(int i=0;i<cards.size();i++){
System.out.println(cards.get(i).getCardName());


    }

}


    private ArrayList<Card> InitializeDeck(){
ArrayList<Card> cards = new ArrayList<Card>(52);

        String[] suits ={"♠", "♥", "♣", "♦"};
        String[] ranks = {"A","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

       for(int i=0;i<suits.length;i++){


           for(int a=0;a<ranks.length;a++){
               cards.add(new Card(suits[i],ranks[a]));

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
        ArrayList<Card> newCards = new ArrayList<Card>();
        newCards.addAll(secondHalf);
        newCards.addAll(firstHalf);
        cards = newCards;
    }

    public ArrayList<Card> getCards() {

        return cards;
    }

}
