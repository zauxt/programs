// Name: Benjamin Bush

public class DoubleDoubleLL {

    // Represents each node in the doubly linked list
    private class Node {
        double data;
        Node next;
        Node prev;

        public Node(double data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }  

    // Initalizes the head, tail, and current
    private Node head;
    private Node tail;
    private Node curr;

    public DoubleDoubleLL() {
        head = null;
        tail = null;
        curr = null;
    }

    // Moves current reference foward by one node
    public void gotoNext() {
        if (curr != null) curr = curr.next;
    }

    // Moves current reference backwards by one node
    public void gotoPrev() {
        if (curr != null) curr = curr.prev;
    }

    // Resets current reference to the head
    public void reset() {
        curr = head;
    }

    // Moves current reference to the tail
    public void gotoEnd() {
        curr = tail;
    }

    // Checks if there are more nodes to traverse
    public boolean hasMore() {
        return (curr != null);
    }

    // Returns the data from the current reference
    public double getCurrent() {
        return (curr == null) ? null : curr.data;
    }

    // Changes the data at the current reference
    public void setCurrent(double data) {
        if (curr != null) curr.data = data;
    }

    // Adds new node to the end and updates applicable references
    public void add(double data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Adds new node next to current and updates applicable references
    public void addAfterCurrent(double data) {
        if (curr == null) return;

        Node newNode = new Node(data);
        newNode.next = curr.next;
        newNode.prev = curr;

        if (curr.next != null) curr.next.prev = newNode;
        else tail = newNode;

        curr.next = newNode;
    }

    // Removes a specified node given data and updates applicable references
    public void remove(double data) {
        Node temporary = head;

        while (temporary != null) {
            if (temporary.data == data) {
                // Case 1: Node is the head
                if (temporary == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                }
                // Case 2: Node is the tail
                else if (temporary == tail) {
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                }
                // Case 3: Node is somewhere in the middle
                else {
                    temporary.prev.next = temporary.next;
                    temporary.next.prev = temporary.prev;
                }
            }
            temporary = temporary.next;
        }
    }

    // Removes the current node and updates applicable references
    public void removeCurrent() {
        if (curr == null) return;

        // Case 1: Node is the head
        if (curr == head) {
            head = head.next;
            if (head != null) head.prev = null;
        }

        // Case 1: Node is the tail
        else if (curr == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
        }

        // Case 1: Node is somewhere in the middle
        else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
        
        curr = curr.next;
    }

    // Prints out the whole list
    public void print() {
        Node temporary = head;
        while (temporary != null) {
            System.out.println(temporary.data);
            temporary = temporary.next;
        }
    }

    // Checks if the list contains a specific value 
    public boolean contains(double data) {
        Node temporary = head;
        while (temporary != null) {
            if (temporary.data == data) return true;
            temporary = temporary.next;
        }
        return false;
    }
	
}