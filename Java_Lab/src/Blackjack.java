import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		int seed = Integer.parseInt(args[0]);
		int numOfCom = Integer.parseInt(args[1])-1;
		String hitOrStand = new String();
		
		Deck deck = new Deck();		// Create the deck.		
		deck.shuffle(seed);			// Shuffle the deck.
		
		Scanner sc = new Scanner(System.in);
		
		House house = new House();
		Player player = new Player();
		Computer[] computer = new Computer[numOfCom];
		for(int i=0; i<numOfCom; i++) computer[i] = new Computer();
		
		for(int j=0; j<2; j++) {
			player.myHand[j] = deck.dealCard();
			player.numOFCards++;
			for(int i=0; i<numOfCom; i++) {
				computer[i].computerhand[j] = deck.dealCard();
				computer[i].numOFCards++;
			}
			house.houseHand[j] = deck.dealCard();
			house.numOFCards++;
		}
		
		house.printHidden();
		player.printAllCards("", "");
		if(numOfCom != 0) {
			for(int i=0; i<numOfCom; i++) {
				System.out.print("Player" + (i+2) + ": ");
				computer[i].printAllCards("");
			}
		}
		
		if(house.sumOfAllCards() == 21) {
			System.out.println("");
			System.out.println("---Game Results---");
			house.printAllCards("");
			player.printAllCards("[Lose] ", "");
			if(numOfCom != 0) {
				for(int i=0; i<numOfCom; i++) {
					System.out.print("[Lose] Player" + (i+2) + ": ");
					computer[i].printAllCards("");
				}
			}
		}
		else {
			System.out.println("");
			System.out.println("---Player1 turn---");
			player.printAllCards("", "");
			
			hitOrStand = sc.nextLine();
			while(!hitOrStand.equals("Stand") && !hitOrStand.equals("Bust")) {
				if(hitOrStand.equals("Hit")) {
					player.myHand[player.numOFCards] = deck.dealCard();
					player.numOFCards++;
					if(player.sumOfAllCards() <= 21) {
						player.printAllCards("", "");
						hitOrStand = sc.nextLine();
					}
					else {
						player.Busted = 1;
						hitOrStand = "Bust";
						player.printAllCards("", " - Bust!");
					}
				}
			}
			if(hitOrStand.equals("Stand")) player.printAllCards("", "");
			sc.close();
			
			
			for(int i=0; i<numOfCom; i++) {
				System.out.println("");
				System.out.println("---Player" + (i+2) + " turn---");
				System.out.print("Player" + (i+2) + ": ");
				computer[i].printAllCards("");
				
				do {
					if(computer[i].sumOfAllCards() < 14) {
						hitOrStand = "Hit";
						System.out.println("Hit");
						computer[i].computerhand[computer[i].numOFCards] = deck.dealCard();
						computer[i].numOFCards++;
						if(computer[i].sumOfAllCards() > 21) {
							computer[i].Busted = 1;
							hitOrStand = "Bust";
							System.out.print("Player" + (i+2) + ": ");
							computer[i].printAllCards(" - Bust!");
						}
						else {
							hitOrStand = "Hit";
							System.out.print("Player" + (i+2) + ": ");
							computer[i].printAllCards("");
						}
					}
					
					if(computer[i].sumOfAllCards() > 17 && computer[i].sumOfAllCards() <= 21) {
						hitOrStand = "Stand";
						System.out.println("Stand");
						System.out.print("Player" + (i+2) + ": ");
						computer[i].printAllCards("");
					}
					
					if(computer[i].sumOfAllCards() >= 14 && computer[i].sumOfAllCards() <= 17){
						Random random = new Random();
						int is_hit = (int)(random.nextInt(2));
						
						if(is_hit == 1) {
							System.out.println("Hit");
							computer[i].computerhand[computer[i].numOFCards] = deck.dealCard();
							computer[i].numOFCards++;
							if(computer[i].sumOfAllCards() > 21) {
								computer[i].Busted = 1;
								hitOrStand = "Bust";
								System.out.print("Player" + (i+2) + ": ");
								computer[i].printAllCards(" - Bust!");
							}
							else {
								hitOrStand = "Hit";
								System.out.print("Player" + (i+2) + ": ");
								computer[i].printAllCards("");
							}
						}
						
						if(is_hit == 0) {
							hitOrStand = "Stand";
							System.out.println("Stand");
							System.out.print("Player" + (i+2) + ": ");
							computer[i].printAllCards("");
						}
					}
					
				}while(hitOrStand.equals("Hit"));
			}
			

			System.out.println("");
			System.out.println("---House turn---");
			house.printAllCards("");
			
			do {
				if(house.sumOfAllCards() <= 16) {
					hitOrStand = "Hit";
					System.out.println("Hit");
					house.houseHand[house.numOFCards] = deck.dealCard();
					house.numOFCards++;
					if(house.sumOfAllCards() <= 21) {
						house.printAllCards("");
					}
					else {
						house.Busted = 1;
						hitOrStand = "Bust";
						house.printAllCards(" - Bust!");
					}
				}
				if(house.sumOfAllCards() > 16 && hitOrStand != "Bust") {
					hitOrStand = "Stand";
					System.out.println("Stand");
					house.printAllCards("");
				}
			}while(hitOrStand.equals("Hit"));
			
			System.out.println("");
			System.out.println("---Game Results---");
			
			if(house.Busted == 1)
				house.printAllCards(" - Bust!");
			else house.printAllCards("");

			if(player.Busted != 1 && house.Busted != 1) {
				if(player.sumOfAllCards() > house.sumOfAllCards()) player.printAllCards("[Win]  ", "");
				else if(player.sumOfAllCards() < house.sumOfAllCards()) player.printAllCards("[Lose] ", "");
				else player.printAllCards("[Draw] ", "");
			}
			if(player.Busted != 1 && house.Busted == 1) player.printAllCards("[Win]  ", "");
			if(player.Busted == 1) player.printAllCards("[Lose] ", " - Bust!");
			
			for(int i=0; i<numOfCom; i++) {
				if(computer[i].Busted != 1 && house.Busted != 1) {
					if(computer[i].sumOfAllCards() > house.sumOfAllCards()) {
						System.out.print("[Win]  Player" + (i+2) + ": ");
						computer[i].printAllCards("");
					}
					else if(computer[i].sumOfAllCards() < house.sumOfAllCards()) {
						System.out.print("[Lose] Player" + (i+2) + ": ");
						computer[i].printAllCards("");
					}
					else {
						System.out.print("[Draw] Player" + (i+2) + ": ");
						computer[i].printAllCards("");
					}
					
				}
				if(computer[i].Busted != 1 && house.Busted == 1) {
					System.out.print("[Win]  Player" + (i+2) + ": ");
					computer[i].printAllCards("");
				}
				if(computer[i].Busted == 1) {
					System.out.print("[Lose] Player" + (i+2) + ": ");
					computer[i].printAllCards(" - Bust!");
				}
			}
		}	
	}
}

class Card{
	public int value;
	public int suit;
	
	public Card() {}
	public Card(int theValue, int theSuit) {
		this.value = theValue;
		this.suit = theSuit;
	}
	public int isAce() {
		int result = 0;
		if(this.value == 'A') result = 1;
		return result;
	}
	
	public int getGameValue() {
		int result;
		if(this.value == 'A') result = 11;
		else if(this.value == 'J' || this.value == 'Q' || this.value == 'K') result = 10;
		else result= this.value;
		return result;
	}
	public String getRealValue() {
		String result;
		if(this.value == 'A') result = 'A'+"";
		else if(this.value == 'J') result = 'J'+"";
		else if(this.value == 'Q') result = 'Q'+"";
		else if(this.value == 'K') result = 'K'+"";
		else if(this.value == 10) result = "10"+"";
		else result = (char)(this.value+48)+""; // Use ascii code to convert number 2~9 into character.
		return result;
	}
	public char getRealSuit() {
		char result;
		if(this.suit == 'c') result = 'c';
		else if(this.suit == 'h') result = 'h';
		else if(this.suit == 'd') result = 'd';
		else result = 's';
		return result;
	}
	
}

class Deck{
	private Card[] deck;
	private int cardsUsed;
	
	Deck(){
		int index = 0;
		this.deck = new Card[52];
		
		deck[index++] = new Card('A', 'c');
		deck[index++] = new Card('A', 'h');
		deck[index++] = new Card('A', 'd');
		deck[index++] = new Card('A', 's');
		
		for(int i=2; i<=10; i++) {
			deck[index++] = new Card(i, 'c');
			deck[index++] = new Card(i, 'h');
			deck[index++] = new Card(i, 'd');
			deck[index++] = new Card(i, 's');
		}
		
		deck[index++] = new Card('J', 'c');
		deck[index++] = new Card('J', 'h');
		deck[index++] = new Card('J', 'd');
		deck[index++] = new Card('J', 's');
		
		deck[index++] = new Card('Q', 'c');
		deck[index++] = new Card('Q', 'h');
		deck[index++] = new Card('Q', 'd');
		deck[index++] = new Card('Q', 's');
		
		deck[index++] = new Card('K', 'c');
		deck[index++] = new Card('K', 'h');
		deck[index++] = new Card('K', 'd');
		deck[index++] = new Card('K', 's');
	}
	
	public void shuffle(int seed) {
		Random random = new Random(seed);
		for(int i=deck.length -1; i>0; i--) {
			int rand = (int)(random.nextInt(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed = 0;
	}
	public Card dealCard() {
		if(cardsUsed == deck.length)
			throw new IllegalStateException("No cards are left in the deck.");
		cardsUsed++;
		return deck[cardsUsed-1];
	}
}

class Hand{}						// Set of cards in your hand

class Computer extends Hand{		// player automatically participates
	public Card[] computerhand;
	public int numOFCards;
	public int Busted;
	
	Computer(){
		computerhand = new Card[12];
		for(int i=0; i<12; i++)
			computerhand[i] = new Card();
		numOFCards = 0;
		Busted = 0;
	}
	
	public int sumOfAllCards() {
		int sum = 0;
		int numOfAce = 0;
		for(int i=0; i<numOFCards; i++) {
			sum += computerhand[i].getGameValue();
			numOfAce += computerhand[i].isAce();
		}
		if(sum > 21 && numOfAce != 0) {
			while(sum > 21 && numOfAce > 0) {
				sum -= 10;
				numOfAce--;
			}
		}	
		return sum;	
	}
	
	public void printAllCards(String sur) {
		for(int i=0; i<numOFCards-1; i++) {
			System.out.print(computerhand[i].getRealValue());
			System.out.print(computerhand[i].getRealSuit() + ", ");
		}
		System.out.print(computerhand[numOFCards-1].getRealValue());
		System.out.println(computerhand[numOFCards-1].getRealSuit() + " (" + this.sumOfAllCards() + ")" + sur);
	}

}

class Player extends Hand{			// player you control
	public Card[] myHand;
	public int numOFCards;
	public int Busted;
	
	Player(){
		myHand = new Card[12];
		for(int i=0; i<12; i++)
			myHand[i] = new Card();
		numOFCards = 0;
		Busted = 0;

	}
	
	public int sumOfAllCards() {
		int sum = 0;
		int numOfAce = 0;
		for(int i=0; i<numOFCards; i++) {
			sum += myHand[i].getGameValue();
			numOfAce += myHand[i].isAce();
		}
		if(sum > 21 && numOfAce != 0) {
			while(sum > 21 && numOfAce > 0) {
				sum -= 10;
				numOfAce--;
			}
		}	
		return sum;	
	}
	
	public void printAllCards(String pre, String sur) {
		System.out.print(pre + "Player1: ");
		for(int i=0; i<numOFCards-1; i++) {
			System.out.print(myHand[i].getRealValue());
			System.out.print(myHand[i].getRealSuit() + ", ");
		}
		System.out.print(myHand[numOFCards-1].getRealValue());
		System.out.println(myHand[numOFCards-1].getRealSuit() + " (" + this.sumOfAllCards() + ")" + sur);
	}
}

class House extends Hand{
	public Card[] houseHand;
	public int numOFCards;
	public int Busted;
	
	House(){
		houseHand = new Card[12];
		for(int i=0; i<12; i++)
			houseHand[i] = new Card();
		numOFCards = 0;
		Busted = 0;
	}
	
	public int sumOfAllCards() {
		int sum = 0;
		int numOfAce = 0;
		for(int i=0; i<numOFCards; i++) {
			sum += houseHand[i].getGameValue();
			numOfAce += houseHand[i].isAce();
		}
		if(sum > 21 && numOfAce != 0) {
			while(sum > 21 && numOfAce > 0) {
				sum -= 10;
				numOfAce--;
			}
		}	
		return sum;	
	}
	
	public void printHidden() {
		System.out.print("House: ");
		System.out.print("Hidden, ");
		System.out.print(houseHand[1].getRealValue());
		System.out.println(houseHand[1].getRealSuit());
	}
	
	public void printAllCards(String sur) {
		System.out.print("House: ");
		for(int i=0; i<numOFCards-1; i++) {
			System.out.print(houseHand[i].getRealValue());
			System.out.print(houseHand[i].getRealSuit() + ", ");
		}
		System.out.print(houseHand[numOFCards-1].getRealValue());
		System.out.println(houseHand[numOFCards-1].getRealSuit() + " (" + this.sumOfAllCards() + ")" + sur);
	}
}