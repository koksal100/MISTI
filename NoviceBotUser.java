package SE116PROJECT;

import java.util.Random;

public class NoviceBotUser extends AbstractUser {

    NoviceBotUser(String name) {
        super(name);
    }

    NoviceBotUser() {

    }

    public Card findBestCardToPlay() {
        Random rand = new Random();
        int randIndex = rand.nextInt(getCurrentCards().size());


        return getCurrentCards().get(randIndex);
    }
}
