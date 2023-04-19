package SE116PROJECT;

import java.util.ArrayList;
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


		//Initializing users
		System.out.println("Enter your name:");
		String name=scan.next();
		HumanUser humanUser=new HumanUser(name);


		RegularBotUser regularBotUser=new RegularBotUser("RegularBot");
		NoviceBotUser noviceBotUser=new NoviceBotUser("NoviceBot");
		ExpertBotUser expertBotUser=new ExpertBotUser("ExpertBot");


		//Creating Board
		ArrayList<Card> board=new ArrayList<>();

        //Creating possible players
		AbstractUser player2=null;
		AbstractUser player3=null;
		AbstractUser player4=null;

		//Getting game options from user

		System.out.println("Please select an option: 1-Two Players 2-Three Players 3-Four Players");
		String playerNumber=scan.next();

		int intPlayerNumber=Integer.parseInt(playerNumber);
		int gamePlayerNumber;
		int totalRoundNumber=0;
		switch (intPlayerNumber){
			case 1:
				gamePlayerNumber=2;
				totalRoundNumber =48/gamePlayerNumber*4;
				break;
			case 2:
				gamePlayerNumber=3;
				totalRoundNumber =48/gamePlayerNumber*4;
				break;
			case 3:
				gamePlayerNumber=4;
				totalRoundNumber =48/gamePlayerNumber*4;
				break;
		}
//Choosing expertness levels of players
		String expertnessLevel= "";
		int intExpertnessLevel;

		switch (intPlayerNumber){

			case 1:
				System.out.println("Please choose second player's expertness level: 1-NoviceBot 2-RegularBot 3-ExpertBot");
				 expertnessLevel=scan.next();
				 intExpertnessLevel=Integer.parseInt(expertnessLevel);
				player2=choosingAPlayersExpertnessLevel(intExpertnessLevel,noviceBotUser,regularBotUser,expertBotUser);
				break;
			case 2:
				System.out.println("Please choose second player's expertness level: 1-NoviceBot 2-RegularBot 3-ExpertBot");
				 expertnessLevel=scan.next();
				intExpertnessLevel=Integer.parseInt(expertnessLevel);
				player2=choosingAPlayersExpertnessLevel(intExpertnessLevel,noviceBotUser,regularBotUser,expertBotUser);
				System.out.println("Please choose third player's expertness level: 1-NoviceBot 2-RegularBot 3-ExpertBot");
				expertnessLevel=scan.next();
				intExpertnessLevel=Integer.parseInt(expertnessLevel);
				player3=choosingAPlayersExpertnessLevel(intExpertnessLevel,noviceBotUser,regularBotUser,expertBotUser);
				break;
			case 3:
				System.out.println("Please choose second player's expertness level: 1-NoviceBot 2-RegularBot 3-ExpertBot");
				expertnessLevel=scan.next();
				intExpertnessLevel=Integer.parseInt(expertnessLevel);
				player2=choosingAPlayersExpertnessLevel(intExpertnessLevel,noviceBotUser,regularBotUser,expertBotUser);
				System.out.println("Please choose third player's expertness level: 1-NoviceBot 2-RegularBot 3-ExpertBot");
				expertnessLevel=scan.next();
				intExpertnessLevel=Integer.parseInt(expertnessLevel);
				player3=choosingAPlayersExpertnessLevel(intExpertnessLevel,noviceBotUser,regularBotUser,expertBotUser);
				System.out.println("Please choose fourth player's expertness level: 1-NoviceBot 2-RegularBot 3-ExpertBot");
				expertnessLevel=scan.next();
				intExpertnessLevel=Integer.parseInt(expertnessLevel);
				player4=choosingAPlayersExpertnessLevel(intExpertnessLevel,noviceBotUser,regularBotUser,expertBotUser);

		}

//Game
	for(int roundNumber=1;roundNumber<=totalRoundNumber;roundNumber++){


	}





	}
public static AbstractUser choosingAPlayersExpertnessLevel(int intExpertnessLevel,NoviceBotUser noviceBotUser,RegularBotUser regularBotUser,ExpertBotUser expertBotUser){
	switch (intExpertnessLevel){
		case 1:
			return noviceBotUser;

		case 2:
			return regularBotUser;
		case 3:
			return expertBotUser;


	}
	return null;
	}



	public static void dealHands(int roundNumber,int gamePlayerNumber,Deck gameDeck,HumanUser humanUser,AbstractUser player2,ArrayList<Card> board){
if(roundNumber==1){
	int cardIndex=0;
	for (int i=0;i<4;i++) {
		humanUser.addToCurrentCards(gameDeck.getCards().get(cardIndex));
		cardIndex++;
		board.add(gameDeck.getCards().get(cardIndex));
		cardIndex++;
		player2.addToCurrentCards(gameDeck.getCards().get(cardIndex));
	}
}

	}
	public static void dealHands(int roundNumber,Deck gameDeck,AbstractUser player2,AbstractUser player3,ArrayList<Card> board){

	}
	public static void dealHands(int roundNumber,Deck gameDeck,AbstractUser player2,AbstractUser player3,AbstractUser player4,ArrayList<Card> board){

	}
}
