// Name: Benjamin Bush

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

class LinkedBST<T extends Comparable<T>> {
    class Node {
        T data;
        Node left, right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    public LinkedBST() {
        this.root = null;
    }

    public void add(T data) {
        root = addRecursively(root, data);
    }

    private Node addRecursively(Node node, T data) {
        // Current node is empty - return a new node containing the data
        if (node == null) return new Node(data); 
        // Data is less than current node - recurse to left subtree
        if (data.compareTo(node.data) < 0) node.left = addRecursively(node.left, data);
        // Data is greater than current node - recurse to right subtree
        else if (data.compareTo(node.data) > 0) node.right = addRecursively(node.right, data);
        // Return node after inserting the new data
        return node;
    }

    public void remove(T data) {
        root = removeRecursively(root, data);
    }

    private Node removeRecursively(Node node, T data) {
        // Reached end of branch without finding data - return null to signify that
        if (node == null) return null;
        // Data is less than current node - recurse to left subtree
        if (data.compareTo(node.data) < 0) node.left = removeRecursively(node.left, data);
        // Data is greater than current node - recurse to right subtree
        else if (data.compareTo(node.data) > 0) node.right = removeRecursively(node.right, data);
        // We've found the node we're searching for
        else {
            // Node is a leaf - it has no children
            if (node.left == null && node.right == null) return null; 
            // Node only has a right child - return right to effectively remove current
            else if (node.left == null) return node.right;
            // Node only has a left child - return left to effectively remove current
            else if (node.right == null) return node.left;
            // Node has two children - namely left & right
            else {
                // Find minimum from right subtree
                Node current = node.right;
                while (current.left != null) current = current.left;

                // Replace current with minimum and remove one that was copied
                node.data = current.data;
                node.right = removeRecursively(node.right, data);
            }
        }
        return node;
    }

    public boolean search(T data) {
        return searchRecursively(root, data);
    }

    private boolean searchRecursively(Node node, T data) {
        if (node == null) return false;
        if (data.compareTo(node.data) == 0) return true;
        else if (data.compareTo(node.data) < 0) return searchRecursively(node.left, data);
        else return searchRecursively(node.right, data);
    }

    public void printPreOrder() {
        printPreOrderRecursively(root);
    }

    private void printPreOrderRecursively(Node node) {
        if (node != null) {
            System.out.println(node.data + " ");
            printPreOrderRecursively(node.left);
            printPreOrderRecursively(node.right);
        }
    }

    public void printInOrder() {
        printInOrderRecursively(root);
    }

    private void printInOrderRecursively(Node node) {
        if (node != null) {
            printInOrderRecursively(node.left);
            System.out.println(node.data + " ");
            printInOrderRecursively(node.right);
        }
    }

    public void printPostOrder() {
        printPostOrderRecursively(root);
    }

    private void printPostOrderRecursively(Node node) {
        if (node != null) {
            printPostOrderRecursively(node.left);
            printPostOrderRecursively(node.right);
            System.out.println(node.data + " ");
        }
    }
}

class Fruit implements Comparable<Fruit> {
    String type;
    double weight;
    
    public Fruit() {
        this.type = "apple";
        this.weight = 1.0;
    }

    public Fruit(String type, double weight) {
        setType(type);
        setWeight(weight);
    }

    public String getType() {
        return this.type;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setType(String type) {
        this.type = (type.equals("Apple") || type.equals("Orange") || type.equals("Banana") || type.equals("Kiwi") || type.equals("Tomato")) ? type : "apple";
    }
    
    public void setWeight(double weight) {
        this.weight = (weight > 0) ? weight : 1.0;
    }

    public String toString() {
        return "Type: " + this.type + " Weight: " + String.valueOf(this.weight);
    }

    public int compareTo(Fruit other) {
        if (other == null) return -1;
        if (this.weight > other.weight) return 1;
        else if (this.weight < other.weight) return -1;
        else return this.type.compareTo(other.type);
    }
}

public class FruitTreeTester {
    public static void main(String[] args) throws IOException {
        LinkedBST<Fruit> fruitTree = new LinkedBST<>();
        System.out.println("Welcome to the fruit tree!\nPlease enter a Fruit File Name");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    
        BufferedReader file = new BufferedReader(new FileReader(console.readLine()));
        String line = new String();
        while ((line = file.readLine()) != null) {
            String[] temporary = line.split("\t");
            if (temporary.length != 2) continue;
            fruitTree.add(new Fruit(temporary[0], Double.valueOf(temporary[1])));
        }
        file.close();

        System.out.println("Populating tree file\nPrinting the in-order traversal");
        fruitTree.printInOrder();

        System.out.println("\nPrinting the pre-order traversal");
        fruitTree.printPreOrder();

        System.out.println("\nPrinting the post-order traversal");
        fruitTree.printPostOrder();

        System.out.println("\nDeleting Apple    0.4859853412170728");
        fruitTree.remove(new Fruit("Apple", 0.4859853412170728));

        System.out.println("Printing in-order traversal");
        fruitTree.printInOrder();
    }
}