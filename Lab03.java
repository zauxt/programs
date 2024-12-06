
// Name: Benjamin Bush

class GroceryItem {
    private String name;
    private double value;

    // Default and parameterized constructors

    public GroceryItem() {
        name = "none";
        value = 0;
    }

    public GroceryItem(String name, double value) {
        try {
            this.name = name;
            this.value = value;
        } 
        catch (Exception e) {System.out.println("Invalid Choice");}
    }

    // Accessor and mutator methods

    public void setName(String name) {
        try {this.name = name;} 
        catch (Exception e) {System.out.println("Invalid Choice");}
    }

    public void setValue(double value) {
        try {this.value = value;} 
        catch (Exception e) {System.out.println("Invalid Choice");}
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    // Returns the grocery item object represented as a string
    public String toString() {
        return "Grocery Item Name: " + name + " Value: " + String.valueOf(value);
    }

    // Checks if this grocery item has the same name and value as another one
    public boolean equals(GroceryItem item) {
        return (this.name.equals(item.name) && this.value == item.value);
    }
}

class GroceryList {
    private class ListNode {
        GroceryItem data;
        ListNode link;

        public ListNode() {
            this.data = null;
            this.link = null;
        }
        
        public ListNode(GroceryItem data) {
            this.data = data;
            this.link = null;
        }
    }

    private ListNode head;
    private ListNode current;
    private ListNode previous;

    public GroceryList() {
        this.head = new ListNode();
        this.current = head;
        this.previous = head;
    }
    
    // Moves the current pointer to the next node in the list
    public void gotoNext() {
        if (current.link != null) { 
            previous = current;    
            current = current.link;
        }
    }

    public GroceryItem getCurrent() {
        return current.data;
    }

    // Updates the current GroceryItem with a new item
    public void setCurrent(GroceryItem item) {
        if (current != null && item != null) current.data = item;
    }
   
     // Adds a new GroceryItem to the end of the list
    public void addItem(GroceryItem item) {
        if (item == null) return; 
        ListNode newNode = new ListNode(item); 
        if (head.data == null) {
            head = newNode;
            current = head;
        }
        else {
            ListNode temporary = head;
            while (temporary.link != null) temporary = temporary.link;
            temporary.link = newNode;
        }
    }
    
    // Inserts a GroceryItem after the current node
    public void addItemAfterCurrent(GroceryItem item) {
        if (item == null || current == null) return;
        ListNode newNode = new ListNode(item); 
        newNode.link = current.link;
        current.link = newNode;
    }
    
    // Removes the current GroceryItem from the list
    public void removeCurrent() {
        if (current == null) return;
        if (current == head) {
            head = head.link; 
            current = head; 
            previous = head;
        } 
        else { 
            previous.link = current.link; 
            current = previous.link;
        }
    }
    
    // Displays all items in the grocery list
    public void showList() {
        ListNode temporary = head;
        while (temporary != null) {
            if (temporary.data != null) System.out.println(temporary.data);
            temporary = temporary.link; 
        }
    }

    // Checks if the list contains a specific GroceryItem
    public boolean contains(GroceryItem item) {
        ListNode temporary = head;
        while (temporary != null) {
            if (temporary.data != null && temporary.data.equals(item)) return true; 
            temporary = temporary.link;
        }
        return false;
    }

    // Calculates the total cost of all GroceryItems in the list
    public double totalCost() {
        double total = 0.0;
        ListNode temporary = head;
        while (temporary != null) {
            if (temporary.data != null) total += temporary.data.getValue();
            temporary = temporary.link;
        }
        return total;
    }
}