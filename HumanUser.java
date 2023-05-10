package SE116PROJECT;
import java.util.Scanner;
public class HumanUser extends AbstractUser {
	HumanUser(String name) {
		super(name);
	}
	public Card findBestCardToPlay() {
		Card card;
		Scanner sc = new Scanner(System.in);
		boolean valid_card_decision = false;
		String decision;
		int card_number = 0;
		while (!valid_card_decision) {
			System.out.println();
			System.out.println("DEAR " + this.getName() + " WHICH CARDS DO YOU WANTS TO CHOOSE");

			for (int i = 0; i < this.getCurrentCards().size(); i++) {

				System.out.print((i + 1) + ":" + this.getCurrentCards().get(i).getCardName() + "  /  ");
			}
			System.out.println();
			decision = sc.next();
			try {
				card_number = Integer.parseInt(decision);
			} catch (Exception e) {
				System.out.println("PLEASE GIVE A VALID VALUE. BE CAREFUL ABOUT WHETHER YOU ENTER A NUMBER OR NOT");
				continue;
			}
			if (card_number > this.getCurrentCards().size()||card_number<1) {
				System.out.println("PLEASE GIVE A VALID VALUE. BE CAREFUL ABOUT HOW MANY CARDS COULD YOUR HAND HAVE.");
			} else {
				valid_card_decision = true;
			}

		}
		card=this.getCurrentCards().get(card_number-1);

		return card;
	}
}
