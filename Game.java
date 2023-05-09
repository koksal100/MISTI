package SE116PROJECT;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Formatter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static Scanner sc = new Scanner(System.in);
    public static AbstractUser lastWinner;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Preparing cards for game
        Deck deck = new Deck();
        ArrayList<AbstractUser> topTenUsers = new ArrayList<>();
        takeTopTenUsersFileTo(topTenUsers);
        boolean isVerbose = verboseController();

        deck.shuffleDeck();
        deck.cutDeck();

        ArrayList<Card> gameDeck = deck.getCards();

        //gameDeck.printDeck(); // sorted CONTROL


        //gameDeck.printDeck();// last deck CONTROL

        /*for(Card card:gameDeck.getCards()) { // value CONTROL
            System.out.println(card.getCardName()+" value: "+card.getValue());
        }*/

        //Creating Board
        ArrayList<Card> board = new ArrayList<>();
        //Creating players and setting game options
        int numberOfPlayers = determinePlayerNumber();
        ArrayList<AbstractUser> players = settingPlayersOptions(numberOfPlayers);
        int numberOfRounds = determineNumberOfRounds(numberOfPlayers);
        Boolean isFirstRound = true;
        int tableTurn;
        //Game
        for (int i = 0; i < numberOfRounds; i++) {
            tableTurn=0;
            if (i == 0) {
                dealHands(true, players, board, gameDeck);
                for (AbstractUser user : players) {
                    if (user instanceof ExpertBotUser expert ) {
                        for (Card card : board) {
                            expert.getAllPlayedCards().add(card);
                        }

                    }
                }
            } else {
                dealHands(false, players, board, gameDeck);

            }
            printBoard(board);
            if(isVerbose){
                System.out.println("Hand "+(i+1));
                for(int userIndex = 0;userIndex<players.size();userIndex++){
                    players.get(userIndex).showCurrentCards();;
                    System.out.println("SCORE:" + players.get(userIndex).getScore());
                }
                for (int a = 0; a < 4; a++) {


                    System.out.print((++tableTurn)+"  ");
                    for(int userIndex = 0;userIndex<players.size();userIndex++){
                        keepTrackForBots(players, board);
                        players.get(userIndex).playCardTo(board);
                        System.out.print(board.get(board.size()-1).getCardName());
                        keepTrackForBots(players, board);
                        evaluatePlayedCard(players.get(userIndex), board);

                    }
                    System.out.println();



                }


            }else{
                for (int a = 0; a < 4; a++) {
                    for (AbstractUser user : players) {
                        System.out.println(user.getName() + "'s hand:");
                        user.showCurrentCards();
                    }
                    printBoard(board);
                    for (AbstractUser user : players) {

                        keepTrackForBots(players, board);

                        System.out.println("IT IS " + user.getName() + "'S TURN");
                        user.showCurrentCards();
                        user.playCardTo(board);
                        keepTrackForBots(players, board);


                        printBoard(board);

                        evaluatePlayedCard(user, board);
                        System.out.println(user.getName() + "'s score is: " + user.getScore());
                    }
                }
            }




        }
        collectLastCards(lastWinner, board);
        whoHasMostCards(players);
        printBoard(board);
        System.out.println("GAME IS FINISHED");
        for (AbstractUser user : players) {
            System.out.println(user.getName() + "'s score is:" + user.getScore());
        }


        for (AbstractUser user : players) {
            user.raceWithOthers(topTenUsers);
        }


        writeToFile(topTenUsers);
    }

    public static boolean verboseController(){
        boolean isVerbose = false;
        System.out.println("Do you want to play in verbose mode?\nPlease press 1 for yes.\nPlease press 2 for no.");

        int choice=0;

        while(true) {
            String decision=sc.nextLine();
            try {
                choice = Integer.parseInt(decision);
            } catch (Exception e) {
                System.out.println("PLEASE GIVE AN INTEGER");
                continue;
            }
            if (choice == 1) {
                isVerbose = true;
                break;
            }
            else if (choice==2) {
                isVerbose = false;
                break;
            }

            else{
                System.out.println("PLEASE ENTER 1 OR 2!");
                continue;
            }
        }

        return isVerbose;
    }
    public static void whoHasMostCards(ArrayList<AbstractUser> users) {
        int userIndex = 0;
        int mostSize = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getcollectedCards().size() > mostSize) {
                userIndex = i;
                mostSize = users.get(i).getcollectedCards().size();
            }
        }
        users.get(userIndex).setScore(users.get(userIndex).getScore() + 5);
    }

    public static void printBoard(ArrayList<Card> board) {
        System.out.println("❮ BOARD ❯");

        for (int i = board.size() - 1; i >= 0; i--) {

            if (i == board.size() - 1) {
                System.out.println("  ▊" + board.get(board.size() - 1).getCardName() + "    ");
                System.out.println();
            } else {
                System.out.print("  ▊" + board.get(i).getCardName() + "    ");
            }
        }
        System.out.println();
        System.out.println("════════════════════════════════════════");
        System.out.println("");


    }

    public static void keepTrackForBots(ArrayList<AbstractUser> players, ArrayList<Card> Board) {
        for (AbstractUser user : players) {
            if (user instanceof ExpertBotUser expert) {

                expert.findTopCard(Board);
                expert.keepTrackOfAllPlayedCards();
                expert.calculateTotalBoardPoint(Board);

            } else if (user instanceof RegularBotUser regular) {
                regular.findTopCard(Board);
                regular.calculateTotalBoardPoint(Board);
            }
        }
    }

    public static int determineNumberOfRounds(int numberOfPlayers) {
        switch (numberOfPlayers) {
            case 2:
                return 6;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return 0;
    } //çalışıyo


    public static int determinePlayerNumber() {
        Scanner scan = new Scanner(System.in);
        int intOption = 0;

        Boolean isOptionAssigned = false;

        while (!isOptionAssigned) {
            System.out.println("Please select an option: 1-Two Players 2-Three Players 3-Four Players");
            String option = scan.next();
            try {
                intOption = Integer.parseInt(option);
            } catch (Exception e) {
                System.out.println("PLEASE GIVE A VALID VALUE. BE CAREFUL ABOUT WHETHER YOU ENTER A NUMBER OR NOT");
                continue;
            }
            if (intOption < 1 || intOption > 4) {
                System.out.println("Please select number between 1 and 3");

            } else {
                isOptionAssigned = true;
            }
        }
        int numberOfPlayers = 0;

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

    public static ArrayList<AbstractUser> settingPlayersOptions(int numberOfPlayers) {
        Scanner scan = new Scanner(System.in);
        int playerOrder = 1;
        int intOption = 0;
        int numberOfHumanPlayer = 0;
        String option = "";
        Boolean isSelectionCompleted = false;
        ArrayList<AbstractUser> players = new ArrayList<AbstractUser>();


        for (int i = 0; i < numberOfPlayers; i++) {
            isSelectionCompleted = false;
            while (!isSelectionCompleted) {
                System.out.println("Please choose player" + playerOrder + "'s type \n 1:Novice bot \n 2:Regular bot \n 3:Expert bot \n 4:Human player");
                option = scan.next();
                try {
                    intOption = Integer.parseInt(option);
                } catch (Exception e) {
                    System.out.println("Please select a valid option!(enter a number)");
                    continue;
                }
                if (intOption < 1 || intOption > 4) {
                    System.out.println("Please give a number between 1 and 4!");
                    continue;
                } else if (numberOfHumanPlayer == 1 && intOption == 4) {
                    System.out.println("There will be maximum one human player.");
                    continue;
                } else {
                    isSelectionCompleted = true;
                }
                playerOrder++;
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
                    String name = scan.next();
                    players.add(new HumanUser(name));
                    numberOfHumanPlayer++;
            }
        }

        return players;
    } //çalışıyo


    public static void dealHands(Boolean isFirstRound, ArrayList<AbstractUser> players, ArrayList<Card> board, ArrayList<Card> gameDeck) {


        if (isFirstRound == true) {
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


    public static void takeTopTenUsersFileTo(ArrayList<AbstractUser> topTenUsers) {
        String[] informations = new String[3];
        Scanner reader = null;
        AbstractUser tempUser = new NoviceBotUser();

        try {
            reader = new Scanner(Paths.get("top_ten_users.txt"));
            while (reader.hasNextLine()) {
                informations = reader.nextLine().split(",");

                switch (informations[2].trim()) {
                    case "RegularBotUser":
                        tempUser = new RegularBotUser(informations[0].trim());
                        break;
                    case "NoviceBotUser":
                        tempUser = new NoviceBotUser(informations[0].trim());
                        break;
                    case "ExpertBotUser":
                        tempUser = new ExpertBotUser(informations[0].trim());
                        break;
                    case "HumanUser":
                        tempUser = new HumanUser(informations[0].trim());
                        break;
                }
                tempUser.setScore(Integer.parseInt(informations[1].trim()));
                topTenUsers.add(tempUser);
            }

        } catch (Exception e) {

        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public static void writeToFile(ArrayList<AbstractUser> topTenUsers) {
        Scanner reader = null;
        Formatter f = null;
        Scanner scan = new Scanner(System.in);
        int top_10_users_index = 0;
        String name;
        String score;
        String type = "";


        try (FileWriter fw = new FileWriter("top_ten_users.txt")) {


            for (int i = 0; i < topTenUsers.size(); i++) {
                reader = new Scanner(Paths.get("top_ten_users.txt"));
                name = topTenUsers.get(i).getName();
                score = Integer.toString(topTenUsers.get(i).getScore());

                if (topTenUsers.get(i) instanceof ExpertBotUser) type = "ExpertBotUser";
                else if (topTenUsers.get(i) instanceof NoviceBotUser) type = "NoviceBotUser";
                else if (topTenUsers.get(i) instanceof RegularBotUser) type = "RegularBotUser";
                else if (topTenUsers.get(i) instanceof HumanUser) type = "HumanUser";

                fw.write(name + ", " + score + ", " + type);
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

    public static void AssignScoreTo(AbstractUser user, ArrayList<Card> boardCards) {
        //this function must be called before the board cards are moved to the user's collected cards.
        if (boardCards.size() == 2) {
            if (boardCards.get(0).getCardface().equals(boardCards.get(1).getCardface())) {
                for (Card card : boardCards) {
                    user.setScore(user.getScore() + card.getValue() * 5);
                }
            } else {
                for (Card card : boardCards) {
                    user.setScore(user.getScore() + card.getValue());
                }
            }
        } else {
            for (Card card : boardCards) {
                user.setScore(user.getScore() + card.getValue());
            }
        }
    }

    public static void evaluatePlayedCard(AbstractUser user, ArrayList<Card> boardCards) {
        if (boardCards.size() > 1) {
            Card lastPlayedCard = boardCards.get(boardCards.size() - 1);
            Card oldLastPlayedCard = boardCards.get(boardCards.size() - 2);
            if (lastPlayedCard.getCardface().equals("J") || lastPlayedCard.getCardface().equals(oldLastPlayedCard.getCardface())) {
                System.out.print("!");
                lastWinner = user;
                AssignScoreTo(user, boardCards);
                user.collectCards(boardCards);
            }
        }
    }

    public static void collectLastCards(AbstractUser user, ArrayList<Card> board) {
        AssignScoreTo(user, board);
        user.collectCards(board);
    }


}
