
// Name: Benjamin Bush

// Imports everything needed
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileIOSolutions {
    static Scanner scanner; 
    static PrintWriter pw;
    public static void pastTense(String input, String output) {
        // Instantiate file input/output and catches IOException to avoid errors
        try {
            scanner = new Scanner(new FileReader(input));
            pw = new PrintWriter(output);
        } 
        catch (IOException e) {}

        // Run while there are more lines to parse
        while (scanner.hasNextLine()) {
            // Parses words and then changes them as necessary before outputing it
            String[] words = scanner.nextLine().split(" ");
            for (String word : words) {
                String result = word.toUpperCase().equals("IS") ? "was" : word;
                System.out.println(result);
                pw.write(result);
            }
        }

        // Closes input and output
        scanner.close(); pw.close();
    }

    public static double totalTubeVolume(String input) {
        // Instantiate file input and catches IOException to avoid errors
        try {scanner = new Scanner(new FileReader(input));} 
        catch (IOException e) {}
        
        double volume = 0;
        // Run while there are more lines to parse
        while (scanner.hasNextLine()) {
            // Parses input and calculates/sums volume as per the formula
            StringTokenizer st = new StringTokenizer(scanner.nextLine());
            st.nextToken(); st.nextToken();
            volume += Math.pow(Double.parseDouble(st.nextToken()), 2) * Math.PI * Double.parseDouble(st.nextToken());
        }

        // Closes input and returns
        scanner.close(); 
        return volume;
    }
}