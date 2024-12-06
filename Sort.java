// Name: Benjamin Bush

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Sort {
    static String[] words;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = new String(), stream = new String();
        while (true) {
            System.out.println("Enter any number of strings and I will sort by SORT’s.  Once you’re done entering sentences enter \"quit\".\n");
            while (!(input = br.readLine()).toUpperCase().equals("QUIT")) stream += String.valueOf(input) + "\t|\t";
            
            // Split the stream of words (or I just realized this is kinda more lines haha) into an array
            words = stream.split("\t\\|\t");
            count = new int[words.length];

            // Uses what I consider to be clever logic to count the number of "SORT" occurrences in each line
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                for (int k = 0; k < word.length() - 3; k++) {
                    if (word.substring(k, k+4).toUpperCase().equals("SORT")) {
                        count[j]++;
                        k += 3;
                    }
                }
            }

            // Sorts the lines based on the "SORT" count using Merge Sort + prints results
            sort(words, count, 0, count.length - 1);
            for (String word : words) System.out.println(word);

            System.out.println("Would you like to sort more Strings?");
            if (!br.readLine().toUpperCase().equals("YES")) break;
        }
        System.out.println("Goodbye!");
    }

    public static void sort(String[] words, int[] count, int left, int right) {
        if (left < right) {
            // Sort (Left Half + Right Half) & Merge Them
            int mid = left + (right - left) / 2;
            sort(words, count, left, mid);
            sort(words, count, mid + 1, right);
            merge(words, count, left, mid, right);
        }
    }

    public static void merge(String[] words, int[] count, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays to hold left/right halves and copys values into them
        int[] Lcount = new int[n1]; String[] Lwords = new String[n1];
        int[] Rcount = new int[n2]; String[] Rwords = new String[n2];

        for (int i = 0; i < n1; i++) {
            Lcount[i] = count[left + i]; Lwords[i] = words[left + i];
        }
        
        for (int j = 0; j < n2; j++) {
            Rcount[j] = count[mid + 1 + j]; Rwords[j] = words[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (Lcount[i] <= Rcount[j]) {
                count[k] = Lcount[i]; words[k] = Lwords[i];
                i++;
            }
            else {
                count[k] = Rcount[j]; words[k] = Rwords[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            count[k] = Lcount[i]; words[k] = Lwords[i];
            i++;
            k++;
        }

        while (j < n2) {
            count[k] = Rcount[j]; words[k] = Rwords[j];
            j++;
            k++;
        }
    }
}
