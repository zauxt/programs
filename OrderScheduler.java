// Name: Benjamin Bush

class Order implements Comparable<Order> {
    private String customer;
    private String order;
    private int cookingTime;
    private int arrivalTime;
    private int timeLeft;

    // Constructors for the order to initalize valid values

    public Order() {
        this.customer = "none";
        this.order = "none";
        this.cookingTime = 1;
        this.arrivalTime = 0;
        this.timeLeft = 1;
    }

    public Order(String customer, String order, int cookingTime, int arrivalTime) {
        this.customer = (customer != null) ? customer : "none";
        this.order = (order != null) ? order : "none";
        this.cookingTime = (cookingTime > 1) ? cookingTime : 1;
        this.arrivalTime = (arrivalTime > 0) ? arrivalTime : 0;
        this.timeLeft = this.cookingTime;
    }

    // Getter and setter methods

    public String getCustomer() {
        return customer;
    }

    public String getOrder() {
        return order;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setCustomer(String customer) {
        if (customer != null) this.customer = customer;
    }

    public void setOrder(String order) {
        if (order != null) this.order = order;
    }

    public void setCookingTime(int cookingTime) {
        if (cookingTime > 0) {
            this.cookingTime = cookingTime;
            this.timeLeft = cookingTime;
        }
    }

    public void setArrivalTime(int arrivalTime) {
        if (arrivalTime >= 0) this.arrivalTime = arrivalTime;
    }

    public void setTimeLeft(int timeLeft) {
        if (timeLeft >= 0) this.timeLeft = timeLeft;
    }
    
    public int compareTo(Order otherOrder) {
        return Integer.compare(this.cookingTime, otherOrder.cookingTime);
    }

    public void cookForOneMinute() {
        if (timeLeft > 0) timeLeft--;
    }

    public boolean isDone() {
        return timeLeft == 0;
    }

    public String toString() {
        return "Customer: " + customer + ", Order: " + order + ", Cooking Time Left: " + timeLeft;
    }
}

class MinHeap {
    private Order[] heap;
    private int size;

    public MinHeap() {
        heap = new Order[10];
        size = 0;
    }

    public void add(Order item) {
        if (size == heap.length) resize();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    // Removes and returns the root element (minimum in this case)
    public Order remove() {
        if (isEmpty()) return null;
        Order min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return min;
    }

    private void swap(int first, int second) {
        Order temporary = heap[first];
        heap[first] = heap[second];
        heap[second] = temporary;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    // Doubles the size of the heap when it fills up
    private void resize() {
        Order[] fresh = new Order[heap.length * 2];
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

    private Order getLeft(int index) { return heap[getLeftIndex(index)]; }
    private Order getRight(int index) { return heap[getRightIndex(index)]; }
    private Order getParent(int index) { return heap[getParentIndex(index)]; }
}

public class OrderScheduler {
    private MinHeap orders;
    private Order current;
    private int minute;
    private int total;
    private int wait;

    public OrderScheduler() {
        this.orders = new MinHeap();
        this.current = null;
        this.minute = 0;
        this.total = 0;
        this.wait = 0;
    }

    public void addOrder(Order newOrder) {
        if (current == null) current = newOrder;
        else orders.add(newOrder);
        total++;
    }

    // Advances the simulation by one minute
    public void advanceOneMinute() {
        minute++;

        if (current != null) {
            current.cookForOneMinute();

            if (current.isDone()) {
                wait += minute - current.getArrivalTime();
                current = orders.remove();
            }
        }
    }

    public boolean isDone() {
        return current == null;
    }

    // Calculates and returns the average waiting time for all orders
    public double getAverageWaitingTime() {
        return (total == 0) ? 0 : (double) wait / total;
    }

    public String getCurrentOrder() {
        return current.toString();
    }
}
