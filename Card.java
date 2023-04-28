package SE116PROJECT;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Card {
	private int value;
	private String suit;
	private String cardface;
	private String cardName;

	private static String fileAdress=getFileName();
	Card(String suit, String cardface) {
		setCardface(cardface);
		setSuit(suit);
		setValue(fileAdress);

	}
	Card() {


	}

	public String getCardName() {
		return suit + " " + cardface;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {

		this.suit = suit;
	}

	public String getCardface() {
		return cardface;
	}

	public void setCardface(String cardface) {
		this.cardface = cardface;
	}

	public int getValue() {
		return value;
	}

	public void setValue(String fileAdress) {
		Scanner reader = null;
		String[] informations = new String[2];
		boolean isAssigned = false;
		int i = 0;
		try {

			reader = new Scanner(fileAdress);
			while (reader.hasNextLine()) {
				informations = reader.nextLine().split(" ");
				if ((this.getSuit() + this.getCardface()).equals(informations[0])
						|| ("*" + this.getCardface()).equals(informations[0])
						|| (this.getSuit() + "*").equals(informations[0])) {
					this.value = Integer.parseInt(informations[1]);
					isAssigned = true;
					break;
				}

			}
			if (!isAssigned) {
				this.value = 1;
			}

		} catch (Exception e) {

		} finally {
			if (reader != null) {
				reader.close();
			}
		}

	}

	public static String getFileName(){
		Scanner scan = new Scanner(System.in);
		Path path;
		String fileName="";
		boolean isAssigned=false;
		int i =0;
		while(!isAssigned){
			System.out.println("PLEASE GIVE THE ADDRESS OF THE FILE:");
			fileName= scan.nextLine();
			path = Paths.get(fileName);
			if(Files.exists(path)){
				isAssigned=true;
				System.out.println("FILE IS FOUND!");
				break;
			}else{
				System.out.println("FILE DOES NOT EXIST! TRY IT AGAIN.");
			}
		}

		return fileName;
	}
}
