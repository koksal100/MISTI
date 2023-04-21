package SE116PROJECT;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Formatter;
import java.io.FileWriter;import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		//Preparing cards for game
		Deck gameDeck = new Deck();
		//gameDeck.printDeck(); // CONTROL
		gameDeck.shuffleDeck();



		//gameDeck.printDeck();// CONTROL
		for(Card card:gameDeck.getCards()) { //CONTROL
			System.out.println(card.getCardName()+" value: "+card.getValue());
		}
		//Creating Board
		ArrayList<Card> board=new ArrayList<>();
		//Creating players and setting game options
		int numberOfPlayers=determinePlayerNumber();
		ArrayList<AbstractUser> players=settingPlayersOptions(numberOfPlayers);
		int numberOfRounds=determineNumberOfRounds(numberOfPlayers);
		Boolean isFirstRound=true;




		}

//Game







	public static int determinePlayerNumber() {
		Scanner scan = new Scanner(System.in);
		int intOption=0;
		System.out.println("Please select an option: 1-Two Players 2-Three Players 3-Four Players");
		String option = scan.next();
		Boolean isOptionAssigned=false;
		while(!isOptionAssigned) {
			try {
				intOption = Integer.parseInt(option);
			} catch (Exception e) {
				System.out.println("PLEASE GIVE A VALID VALUE. BE CAREFUL ABOUT WHETHER YOU ENTER A NUMBER OR NOT");
				continue;
			}
			if(intOption<1 || intOption > 4 ){
				System.out.println("Please select number between 1 and 3");

			}else {
				isOptionAssigned=true;
			}
		}
		int numberOfPlayers =0;

		switch (intOption) {
			case 1:
				numberOfPlayers = 2;

				break;
			case 2:
				numberOfPlayers = 3;

				break;
			case 3:
				numberOfPlayers = 4;

				break;
		}
		return numberOfPlayers;
	}





	public static int determineNumberOfRounds(int numberOfPlayers){
		switch (numberOfPlayers){
			case 2:
				return 6;
			case 3:
				return 4;
			case 4:
				return 3;
		}
		return 0;
	}
public static ArrayList<AbstractUser> settingPlayersOptions(int numberOfPlayers){
	Scanner scan = new Scanner(System.in);
	int playerOrder=1;
	int intOption=0;
	int numberOfHumanPlayer=0;
	String option="";
	Boolean isSelectionCompleted=false;
	ArrayList<AbstractUser> players = new ArrayList<AbstractUser>();


	for(int i=0;i<numberOfPlayers;i++){
	while(!isSelectionCompleted){
  System.out.println("Please choose" +playerOrder+ "'s type \\1:Novice bot\\n2:Regular bot\\n3:Expert bot\\n4:Human player");
  option= scan.next();
	}try {
			intOption = Integer.parseInt(option);
		} catch (Exception e) {
			System.out.println("Please select a valid option!(enter a number)");
			continue;
		}if (intOption < 1 || intOption > 4) {
			System.out.println("Please give a number between 1 and 4!");
			continue;
		} else if (numberOfHumanPlayer == 1) {
			System.out.println("There will be maximum one human player.");
			continue;
		} else {
			isSelectionCompleted = true;
		}
	}
	switch (intOption) {
		case 1:
			players.add(new NoviceBotUser());
			break;
		case 2:
			players.add(new RegularBotUser());
			break;
		case 3:
			players.add(new ExpertBotUser());
			break;
		case 4:
			System.out.println("Please enter your name:");
			String name=scan.next();
			players.add(new HumanUser(name));
			numberOfHumanPlayer++;
	}

	return players;
	}



	public static void dealHands(Boolean isFirstRound,ArrayList<AbstractUser> players,ArrayList<Card> board,ArrayList<Card> gameDeck) {


			if (isFirstRound==true) {
				for (int a = 0; a < 4; a++) {//board
					board.add(gameDeck.get(0));
					gameDeck.remove(0);

				}

				for (int a = 0; a < 4; a++) {
					for (AbstractUser player : players) {
						player.addToCurrentCards(gameDeck.get(0));
						gameDeck.remove(0);

					}


				}


			} else {
				for (int a = 0; a < 4; a++) {
					for (AbstractUser player : players) {
						player.addToCurrentCards(gameDeck.get(0));
						gameDeck.remove(0);


					}

				}
			}




	}
	public void showTopTenUsers(ArrayList<AbstractUser> users) {

		for (int i = 0; i < users.size(); i++) {

			System.out.println(
					(i + 1) + ". PLAYER'S NAME:" + users.get(i).getName() + ", SCORE:" + users.get(i).getScore());
		}
	}


	public static void takeTopTenUsersFileTo(ArrayList<AbstractUser> topTenUsers){
		String[] informations = new String[3];
		Scanner reader = null;

		try {
			reader = new Scanner(Paths.get("top_ten_users.txt"));
			while (reader.hasNextLine()) {
				AbstractUser temp_user = null;
				informations = reader.nextLine().split(",");

				switch (informations[2]){
					case "RegularBotUser":
						temp_user=new RegularBotUser(informations[0].trim());
						break;
					case "NoviceBotUser":
						temp_user=new NoviceBotUser(informations[0].trim());
						break;
					case "ExpertBotUser":
						temp_user=new ExpertBotUser(informations[0].trim());
						break;
					case "HumanUser":
						temp_user=new HumanUser(informations[0].trim());
						break;
				}
				temp_user.setScore(Integer.parseInt(informations[1].trim()));
				temp_user.raceWithOthers(topTenUsers);

			}
		} catch (Exception e) {

		} finally {
			if (reader != null) {
				reader.close();
			}
		}

	}
	public static void writeToFile(ArrayList<AbstractUser> topTenUsers){
		Scanner reader = null;
		Formatter f = null;
		int top_10_users_index = 0;

		try (FileWriter fw = new FileWriter("top_ten_users.txt")) {


			for(int i = 0;i<topTenUsers.size();i++) {

				reader = new Scanner(Paths.get("top_ten_users.txt"));
				fw.write(topTenUsers.get(i).getName() + ", "
						+ Integer.toString(topTenUsers.get(i).getScore())+", "+ topTenUsers.get(i).getClass().getName());
				fw.write("\n");
			}
		} catch (Exception e) {
			System.out.println("SOMETHING WENT WRONG");
		} finally {
			if (f != null) {
				f.close();
			}
		}
	}

	public static void AssignScoreTo(AbstractUser user,ArrayList<Card> boardCards){
		//this function must be called before the board cards are moved to the user's collected cards.
		if(boardCards.size()==2){
			if(boardCards.get(0).getCardface().equals(boardCards.get(1).getCardface())){
				for(Card card:boardCards){
					user.setScore(user.getScore()+card.getValue()*5);
				}
			}else{
				for(Card card:boardCards){
					user.setScore(user.getScore()+card.getValue());
				}
			}
		} else{
			for(Card card:boardCards){
				user.setScore(user.getScore()+card.getValue());
			}
		}
	}



}
