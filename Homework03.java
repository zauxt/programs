// Name: Benjamin Bush

import java.util.*;
import java.io.*;

// Represents a task with an action and a priority level
class Task {
    private String action;
    private int priority;

    public Task(String action, int priority) {
        this.action = (action != null) ? action : "none";
        this.priority = (priority >= 0 && priority <= 4) ? priority : 4;
    }

    public String getAction() {
        return action;
    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return "\n[Task] Priority: " + priority + " Task: " + action;
    }
}

// Generic linked list implementation to hold tasks
class GenericLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public static class Node<T> {
        Task data;
        Node<T> next;

        public Node(Task data) {
            this.data = data;
            this.next = null;
        }
        
        public Task getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    public GenericLinkedList() {
        head = null;
        tail = null;
    }

    // Add a new task to the linked list if it doesn't already exist
    public void add(String action, int priority) {
        if (!contains(action, priority)) {
            Node<T> newNode = new Node<>(new Task(action, priority));
            if (head == null) {
                head = newNode;
                tail = newNode;
            }
            else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        else System.out.println("Failed to add due to duplicate task");
    }

    // Remove a specific task based on action and priority
    public void remove(String action, int priority) {
        Node<T> curr = head;
        Node<T> prev = null;

        while (curr != null) {
            if (curr.getData().getAction().equals(action) && curr.getData().getPriority() == priority) {
                if (prev == null) head = curr.next;
                else prev.next = curr.next;
                if (curr == tail) tail = prev;
                return;
            }

            prev = curr;
            curr = curr.next;
        }
    }

    // Check if the linked list contains a certain task
    public boolean contains(String action, int priority) {
        Node<T> curr = head;

        while (curr != null) {
            if (curr.getData().getAction().equals(action) && curr.getData().getPriority() == priority) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public String toString() {
        String result = new String();
        Node<T> curr = head;

        while (curr != null) {
            result += curr.getData().toString();
            curr = curr.next;
        }
        
        return result;
    }
}

public class Homework03 {
    private static GenericLinkedList<Task>[] organizedTasks;
    public static void main(String[] args) {
        organizedTasks = new GenericLinkedList[5];
        for (int i = 0; i < organizedTasks.length; i++) organizedTasks[i] = new GenericLinkedList<Task>();
        menu();
    }

    // Display menu for user interaction
    public static void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Task Organizer!");
        while (true) {
            System.out.println("\nEnter 1. To Add a Task\nEnter 2. To Remove a Task\nEnter 3. To Print Tasks To Console\nEnter 4. To Read from a Task File\nEnter 5. To Write to a Task File\nEnter 9. To Quit");
            int choice = Integer.parseInt(input.nextLine());
            if (choice == 1 || choice == 2) {
                System.out.println("Enter the task's priority");
                int priority = Integer.parseInt(input.nextLine());
                System.out.println("Enter the task's action");
                String action = input.nextLine();
                if (choice == 1) addTask(action, priority);
                else removeTask(action, priority);
            }
            else if (choice == 3) printList();
            else if (choice == 4) {
                System.out.println("Enter the file name");
                parseFile(input.nextLine());
            }
            else if (choice == 5) {
                System.out.println("Enter the file name");
                outputList(input.nextLine());
            }
            else if (choice == 9) break;
        }
        System.out.println("Goodbye!");
        input.close();
    }
    
    public static void addTask(String action, int priority) {
        organizedTasks[priority].add(action, priority);
    }

    public static void removeTask(String action, int priority) {
        organizedTasks[priority].remove(action, priority);
    }

    public static void printList() {
        for (int j = 0; j < 5; j++) System.out.print(organizedTasks[j].toString());
        System.out.println();
    }

    public static void outputList(String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name, false))) {
            for (int j = 0; j < 5; j++) writer.write((organizedTasks[j].toString()));
        } catch (Exception e) {}
    }

    public static void parseFile(String name) {
        try (Scanner file = new Scanner(new File(name))) {
            while (file.hasNextLine()) {
                String[] line = file.nextLine().split("\t");
                addTask(line[1], Integer.parseInt(line[0]));
            }
        } catch (Exception e) {}
    }
}