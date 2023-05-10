package SE116PROJECT;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Card {
    private int value;
    private String suit;
    private String cardFace;

    private final static String fileAddress = getFileName();

    Card(String suit, String cardface) {
        setCardface(cardface);
        setSuit(suit);
        setValue(fileAddress);

    }



    public String getCardName() {
        return suit + cardFace;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {

        this.suit = suit;
    }

    public String getCardface() {
        return cardFace;
    }

    public void setCardface(String cardface) {
        this.cardFace = cardface;
    }

    public int getValue() {
        return value;
    }

    public void setValue(String fileAdress) {

        String[] informations;
        boolean isAssigned = false;

        try (Scanner reader = new Scanner(Paths.get(fileAdress))){


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
            System.out.println("SOMETHING WENT WRONG");
        }

    }

    public static String getFileName() {
        Scanner scan = new Scanner(System.in);
        Path path;
        String fileName ;
        while (true) {
            System.out.println("PLEASE GIVE THE ADDRESS OF THE FILE:");
            fileName = scan.nextLine();
            path = Paths.get(fileName);
            if (Files.exists(path) && !fileName.equals("")) {
                System.out.println("FILE IS FOUND!");
                break;
            } else {
                System.out.println("FILE DOES NOT EXIST! TRY IT AGAIN.");
            }
        }

        return fileName;
    }
}
