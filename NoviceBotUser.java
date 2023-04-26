package SE116PROJECT;

import java.util.Random;

public class NoviceBotUser extends AbstractUser {
    private static int numberOfProducedPlayer=1;
    NoviceBotUser(String name) {

        super(name);
    }

    NoviceBotUser() {

        this.setName("NoviceBot"+" "+numberOfProducedPlayer++);

    }

    public Card findBestCardToPlay() {
        Random rand = new Random();
        int randIndex = rand.nextInt(getCurrentCards().size());
        return getCurrentCards().get(randIndex);
    }
}
