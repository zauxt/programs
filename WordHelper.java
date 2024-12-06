
// Name: Benjamin Bush

import java.util.Arrays;

public class WordHelper {
    static int N;
    public static String[] sortByVowels(String[] array) {
        // Initalizes N as length of array, words as copy of parameter, and num as empty int array
        N = array.length;
        String[] words = Arrays.copyOf(array, N);
        int[] num = new int[N];

        // Increments each index of num to represent the number of vowels corresponding to the same index in the words array
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) == 'a' || words[i].charAt(j) == 'e' || words[i].charAt(j) == 'i' || words[i].charAt(j) == 'o' || words[i].charAt(j) == 'u' || words[i].charAt(j) == 'y') {
                    num[i]++;
                }
            }
        }
        
        // Sorts and returns array
        bubbleSort(words, num);
        return words;
    }

    public static String[] sortByConsonants(String[] array) {
        // Initalizes N as length of array, words as copy of parameter, and num as empty int array
        N = array.length;
        String[] words = Arrays.copyOf(array, N);
        int[] num = new int[N];

        // Increments each index of num to represent the number of consonants of the corresponding index in the words array
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (!(words[i].charAt(j) == 'a') && !(words[i].charAt(j) == 'e') && !(words[i].charAt(j) == 'i') && !(words[i].charAt(j) == 'o') && !(words[i].charAt(j) == 'u') && !(words[i].charAt(j) == 'y')) {
                    num[i]++;
                }
            }
        }
        
        // Sorts and returns array
        bubbleSort(words, num);
        return words;
    }

    public static String[] sortByLength(String[] array) {
        // Initalizes N as length of array, words as copy of parameter, and num as empty int array
        N = array.length;
        String[] words = Arrays.copyOf(array, N);
        int[] num = new int[N];

        // Sets each index of num to equal the length of the element of the corresponding index in the words array 
        for (int i = 0; i < N; i++) {
            num[i] = words[i].length();
        }
        
        // Sorts and returns array
        bubbleSort(words, num);
        return words;
    }

    // Utilizes bubble sort algorithm to sort both the num and words array
    public static void bubbleSort(String[] words, int[] num) {
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < N-i-1; j++) {
                if (num[j] > num[j+1]) {
                    int temporary = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temporary;
                    String temp = words[j];
                    words[j] = words[j+1];
                    words[j+1] = temp;
                } 
            }
        }
    }
}
