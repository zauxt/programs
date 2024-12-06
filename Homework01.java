
// Name: Benjamin Bush

// Imports all classes from the java.io and java.util packages
import java.io.*;
import java.util.*;

public class Homework01 {
    // Initializes arrays that hold prize names and prices
    static String[] prize_names = new String[53];
    static double[] prize_prices = new double[53];
    public static void main(String[] args) throws IOException {
        // Initializes the file reader and parses the names and prices of the prizes from prizeList.txt effectively populating the arrays
        BufferedReader br = new BufferedReader(new FileReader("prizeList.txt"));
        for (int j = 0; j < 53; j++) {
            String[] current_line = br.readLine().split("\t");
            if (current_line.length != 2) continue;
            prize_names[j] = current_line[0];
            prize_prices[j] = Double.parseDouble(current_line[1]);
        }
        
		// Changes from file reader to input from console 
        br = new BufferedReader(new InputStreamReader(System.in)); 

		// Prints welcome message and executes the following until the user wants to quit
        System.out.println("Welcome to the showcase show down!");
        while (true) {
			// Prints and validates the 5 prizes and grabs the sum of the prizes prices
            System.out.println("Your prizes are:");
            int sum = parsePrizes();
			// Determines whether the user guessed within [sum-1300, sum] meaning they won or not meaning they lost
            System.out.println("You must guess the total cost of the prizes without going over and within $1,300 of its actual price\nEnter your guess");
            validateGuess(sum, Double.parseDouble(br.readLine()));
			// Continues the game or terminates it based on the users input
            System.out.println("Would you like to quit? Enter \"yes\" to quit");
            String choice = br.readLine();
            if (choice.toUpperCase().equals("YES")) break;
        }
        
		// Exit message
        System.out.println("Goodbye, have a great day!");
    }
	
    public static int parsePrizes() {
		// Instantiates array holding prize indicies and initializes first element
        int[] nums = new int[5];
        nums[0] = new Random().nextInt(53);

		// Populates rest of random prize indicies and checks for duplicate prizes
        for (int j = 1; j < 5; j++) {
            boolean keepGoing = true;
            while (keepGoing) {
                int random = new Random().nextInt(53);
                for (int i = 0; i < j; i++) {
                    if (nums[i] == random) break;
                    else if (i == j-1) {
                        keepGoing = false;
                        nums[j] = random;
                    }
                }
            }            
        }
        
		// Prints the prize names and sums up the prizes prices
        int result = 0;
        for (int k = 0; k < 5; k++) {
            System.out.println(prize_names[nums[k]]);
            result += prize_prices[nums[k]];
        }
	
		// Returns sum of the prizes prices
        return result;
    }
	
	// Prints the actual sum of the prizes prices and determines whether the user won or not based on their guess being within [sum-1300, sum]
    public static void validateGuess(int sum, double guess) {
        System.out.println("The actual cost was " + String.valueOf(sum));
        System.out.println(guess <= sum && guess >= (sum-1300) ? "You won!!!": "You lost.");
    }
}