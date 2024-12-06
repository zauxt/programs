// Name: Benjamin Bush

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MinHeap {
    private Sheep[] heap;
    private int size;

    public MinHeap() {
        heap = new Sheep[10];
        size = 0;
    }

    public void add(Sheep item) {
        if (size == heap.length) resize();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    // Removes and returns the root element (minimum in this case)
    public Sheep remove() {
        if (isEmpty()) return null;
        Sheep min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return min;
    }

    private void swap(int first, int second) {
        Sheep temporary = heap[first];
        heap[first] = heap[second];
        heap[second] = temporary;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    // Doubles the size of the heap when it fills up
    private void resize() {
        Sheep[] fresh = new Sheep[heap.length * 2];
        for (int j = 0; j < heap.length; j++) fresh[j] = heap[j];
        heap = fresh;
    }

    // Moves an element upwards to maintain balance
    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && heap[index].compareTo(getParent(index)) < 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    // Moves an element downwards to maintain balance
    private void heapifyDown() {
        int index = 0;
        while (hasLeft(index)) {
            int smallChild = getLeftIndex(index);
            if (hasRight(index) && getRight(index).compareTo(getLeft(index)) < 0) smallChild = getRightIndex(index);
            if (heap[index].compareTo(heap[smallChild]) <= 0) break;
            else swap(index, smallChild);
            index = smallChild;
        }
    }

    // Helper functions to get indicies and elements and check if they have left/right/parent.
    private int getLeftIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }

    private boolean hasLeft(int index) { return getLeftIndex(index) < size; }
    private boolean hasRight(int index) { return getRightIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }

    private Sheep getLeft(int index) { return heap[getLeftIndex(index)]; }
    private Sheep getRight(int index) { return heap[getRightIndex(index)]; }
    private Sheep getParent(int index) { return heap[getParentIndex(index)]; }
}

class Sheep {
    private String name;
    private int shearingTime;
    private int arrivalTime;

    public Sheep(String name, int shearingTime, int arrivalTime) {
        this.name = name;
        this.shearingTime = shearingTime;
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public int getShearingTime() {
        return shearingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String toString() {
        return "Name: " + name + ", Shear Time: " + String.valueOf(shearingTime) + ", Arrival Time: " + String.valueOf(arrivalTime);    
    }

    public int compareTo(Sheep other) {
        if (this.shearingTime == other.shearingTime) return this.name.compareTo(other.name);
        return Integer.compare(this.shearingTime, other.shearingTime);
    }
    
}

public class SheepShearingScheduler {
    public static void main(String[] args) throws IOException {
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the file name or quit to quit: ");
            String input = br.readLine();
            if (input.toUpperCase().equals("QUIT")) break;
            br = new BufferedReader(new FileReader(input));

            // Parses input
            String line = new String();
            MinHeap heap = new MinHeap();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                if (tokens[2].equals("0")) System.out.println(new Sheep(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])).toString());
                if (tokens.length != 3) continue;
                heap.add(new Sheep(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
            }
    
            while (!heap.isEmpty()) {
                Sheep current = heap.remove();
                System.out.println(current.toString());
            }

            br.close();
        }
    }
}