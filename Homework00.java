
// Name: Benjamin Bush

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Homework00 {
	static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Vector Operations Program!\n");
        
        while (true) {
            printMenu();
            int choice = Integer.parseInt(br.readLine());

            if (choice == 1 || choice == 2) {
                getVectorSize();

                // Instantiates arrays representing vectors + populates two of them with values
                double[] vector1 = getVectorValues("first ");
                double[] vector2 = getVectorValues("second ");
                double[] result = new double[N];
				
                // Performs the addition/subtraction of the vectors based on the choice input
				for (int j = 0; j < N; j++) result[j] = choice == 1 ? vector1[j] + vector2[j] : vector1[j] - vector2[j];
				
                // Prints the results from the performed operations
                System.out.println("Result:");
                for (double element : vector1) System.out.println(element);

                System.out.println("+");
                for (double element : vector2) System.out.println(element);

                System.out.println("=");
                for (double element : result) System.out.println(element);
            }
            
            else if (choice == 3) {
                getVectorSize();

                // Sums up each element squared and prints the square root of that, effectively calculating the magnitude.
                double sum = 0;
                double[] vector = getVectorValues("");
                for (double element : vector) sum += (element * element);
                System.out.println("The magnitude is: " + String.valueOf(Math.sqrt(sum)));
            }
            
            else if (choice == 9) {
                System.out.println("Have a great day!");
                break;
            }
            
            else System.out.println("Invalid Choice!");
        }
    }

    // Basically just a function to print the choices available as needed
    public static void printMenu() {
        System.out.println("Which operation would you like to peform?");
        System.out.println("1. Add 2 Vectors");
        System.out.println("2. Subtract 2 Vectors");
        System.out.println("3. To Find the Magnitude of a Vector");
        System.out.println("9. Quit");
    }

    // Takes and validates the size of the vectors as an input
    public static void getVectorSize() throws IOException {
        System.out.println("Enter the size of the vectors: ");
        N = Integer.parseInt(br.readLine());

        while (N < 1) {
            System.out.println("Invalid Choice! The vector's size must be at least 1: ");
            N = Integer.parseInt(br.readLine());
        }
    }

    // Takes given values from the user and populates the array vector with said values before returning it
    public static double[] getVectorValues(String keyword) throws IOException{
        double[] temporary = new double[N];
        System.out.println("Enter the values for the " + keyword + "vector:");
        for (int i = 0; i < N; i++) temporary[i] = Double.parseDouble(br.readLine());
        return temporary;
    }
}