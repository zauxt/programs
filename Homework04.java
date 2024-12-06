// Name: Benjamin Bush

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;

// Utilized in the queue to store: data + reference to next node
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Uses nodes to create a generic queue
class GenericQueue<T> {
    Node<T> front;
    Node<T> rear;
    int size;

    public GenericQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Add an element to the back of the queue
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        }
        else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    // Remove and return the element from the front of the queue
    public T dequeue() {
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

class Simulator {
    GenericQueue<String> queue = new GenericQueue<>(); // Stores commands
    char[][] board = new char[10][10]; // 10x10 board representation
    int x = 0, y = 0; // Robot current position

    // Parses data from text files for the simulation to run
    public Simulator(String boardFile, String commandFile) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(boardFile));
        for (int j = 0; j < 10; j++) board[j] = file.readLine().toCharArray();
        file = new BufferedReader(new FileReader(commandFile));
        String line = new String();
        while ((line = file.readLine()) != null) queue.enqueue(line);
    }

    // Simulate robot movement based on commands
    public void simulate() {
        int count = 0;
        printBoard();
        while (!queue.isEmpty()) {
            String command = queue.dequeue();
            if (command.equals("")) continue;
            System.out.println("\nCommand " + String.valueOf(count));

            // Validates movements based on correct command syntax, out-of-bounds, and obstacles

            if (command.equals("Move Up")) {
                if (x > 0 && board[x-1][y] != 'X') x--;
                else {
                    System.out.println("CRASH!");
                    break;
                }
            }
            
            else if (command.equals("Move Down")) {
                if (x < 9 && board[x+1][y] != 'X') x++;
                else {
                    System.out.println("CRASH!");
                    break;
                }
            }

            else if (command.equals("Move Left")) {
                if (y > 0 && board[x][y-1] != 'X') y--;
                else {
                    System.out.println("CRASH!");
                    break;
                }
            }

            else if (command.equals("Move Right")) {
                if (y < 9 && board[x][y+1] != 'X') y++;
                else {
                    System.out.println("CRASH!");
                    break;
                }
            }

            else continue;

            printBoard();
            count++;
        }
    }

    // Prints the current state of the board with the robot's position temporarily marked
    public void printBoard() {
        char temp = board[x][y];
        board[x][y] = '*';
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 10; j++) System.out.print(board[k][j]);
            System.out.println();
        }
        board[x][y] = temp;
    }
}

public class Homework04 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Welcome to the Robot Simulator\nEnter the file for the Board");
            String boardFile = br.readLine();
            System.out.println("Enter the file for the Robot Commands");
            String robotFile = br.readLine();

            new Simulator(boardFile, robotFile).simulate();

            System.out.println("Simulation end\nQuit? Enter \"true\" to quit or hit enter to run another simulation");
            if (br.readLine().toUpperCase() == "TRUE") break;
        }
    }
}