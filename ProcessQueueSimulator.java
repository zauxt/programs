// Name: Benjamin Bush

// Represents a process with a name and completion time
class Process {
    private String name;
    private double time;

    public Process(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public Process() {
        this.name = "none";
        this.time = 0.0;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }
    
    public void setCompletionTime(double time) {
        if (time > 0) this.time = time;
        else this.time = 0;
    }

    public String getNameString() {
        return name;
    }

    public double getCompletionTime() {
        return time;
    }

    public String toString() {
        return ("Process Name: " + name + " Completion Time: " + time);
    }
}

// Interface for a generic queue
interface QueueI<T> {
    void enqueue(T item);
    T dequeue();
    T peek();
    void print();
}

// Linked list implementation of a queue
class LLQueue<T> implements QueueI<T> {
    public class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private Node tail;

    public LLQueue() {
        this.head = null;
        this.tail = null;
    }

    // Add an item to the end of the queue
    public void enqueue(T item) {
        Node newNode = new Node(item);
        if (tail != null) tail.next = newNode;
        tail = newNode;
        if (head == null) head = newNode;
    }

    // Remove and return the item from the front of the queue
    public T dequeue() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        if (head == null) tail = null;
        return data;
    }

    // Return the item at the front of the queue without removing it
    public T peek() {
       return (head == null) ? null : head.data;
    }

    // Print all items in the linked list queue
    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
}

// Manages a queue of processes for scheduling
class ProcessScheduler {
    private LLQueue<Process> processQueue;
    private Process currentProcess;

    public ProcessScheduler() {
        processQueue = new LLQueue<>();
        currentProcess = null;
    }

    // Get the currently running process
    public Process getCurrentProcess() {
        return currentProcess;
    }

    // Add a new process to the scheduler
    public void addProcess(Process process) {
        if (currentProcess == null) currentProcess = process;
        else processQueue.enqueue(process);
    }

    // Run the next process in the queue
    public void runNextProcess() {
        currentProcess = processQueue.dequeue();
    }

    // Cancel the current process and move to the next
    public void cancelCurrentProcess() {
        if (currentProcess != null) {
            currentProcess = processQueue.dequeue();
        }
    }

    // Print the current process queue
    public void printProcessQueue() {
        processQueue.print();
    }
}