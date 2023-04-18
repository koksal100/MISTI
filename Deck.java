package SE116PROJECT;

import java.util.ArrayList;
import java.util.Collections;

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

        String[] suits ={"Kupa", "Karo", "Sinek", "Maça"};
        String[] ranks = {"As","2", "3", "4", "5", "6", "7", "8", "9", "10", "Vale", "Kız", "Papaz"};

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

    }
    public ArrayList<Card> getCards() {
        return cards;
    }

}
