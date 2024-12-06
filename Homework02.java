
// Name: Benjamin Bush

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

// Represents each individual game given by their name and console
class Game {
    String name;
    String console;

    public Game(String name, String console) {
        this.name = name;
        this.console = console;
    }

    public String toString() {
        return name + " " + console;
    }
}

// GenericLinkedList that will hold the individual games
class GenericLinkedList<T> {
    private Node head;
    private Node tail;
    private int size;

    public class Node {
        T data;
        Node next;
    
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    public GenericLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public Node getHead() {
        return head;
    }
}

public class Homework02 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static GenericLinkedList<Game> games = new GenericLinkedList<>();
    static GenericLinkedList<Game> results = new GenericLinkedList<>();
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Video Game Database!");
        while (true) {
            // Display menu options for user interaction
            System.out.println("\nEnter 1 to load the video game database\nEnter 2 to search the database\nEnter 3 to print current results to the console\nEnter 4 to print current results to file\nEnter 0 to quit.");
            int input = Integer.parseInt(br.readLine());
            if (input == 1) {
                System.out.println("Enter the file name:");
                load(br.readLine());
            }
            else if (input == 2) {
                System.out.println("Enter the name of the game or '*' for all:");
                String name = br.readLine();
                System.out.println("Enter the name of the console or '*' for all");
                String console = br.readLine();
                search(name, console);
            }
            else if (input == 3) printResults(false);
            else if (input == 4) printResults(true);
            else if (input == 0) break;
            else System.out.println("Invalid Choice!");
        }
        System.out.println("Goodbye!");
    }
    
    // Loads the games into the GenericLinkedList based on the file
    public static void load(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = new String();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 2) games.add(new Game(parts[0], parts[1]));
        }
        reader.close();
    }

    public static void search(String name, String console) {
        GenericLinkedList<Game>.Node current = games.getHead();
        while (current != null) {
            Game game = current.data;
            // Check if the game name and console match the search criteria
            boolean nameMatches = (name.equals("*") || game.name.toUpperCase().contains(name.toUpperCase()));
            boolean consoleMatches = (console.equals("*") || game.console.toUpperCase().contains(console.toUpperCase()));
            if (nameMatches && consoleMatches) {
                results.add(game);
                System.out.println(game.toString());
            }
            current = current.next;
        }
    }

    public static void printResults(boolean file) throws IOException {
        // Prints the search results to the file or the console depending on user input
        if (file) { 
            System.out.println("Enter the file name:");
            String name = br.readLine();
            System.out.println("Would you like to append? True or false?");
            boolean append = Boolean.parseBoolean(br.readLine());
            BufferedWriter writer = new BufferedWriter(new FileWriter(name, append));
            GenericLinkedList<Game>.Node current = results.getHead();
            while (current != null) {
                writer.write(current.data.toString() + "\n");
                current = current.next;
            }
            writer.close();
        }
        else {
            GenericLinkedList<Game>.Node current = results.getHead();
            while (current != null) {
                System.out.println(current.data.toString() + "\n");
                current = current.next;
            }
        }
    }
}