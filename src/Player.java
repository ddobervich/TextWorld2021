import java.util.ArrayList;

public class Player {
    private String name, description;
    private Level.Room currentRoom;
    private ArrayList<Item> inventory;

    public Player(String name) {
        this.name = name;
        this.description = "";
        this.currentRoom = null;
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item i) {
        inventory.add(i);
    }

    public Item removeItem(String name) {
        name = name.trim();
        int index = getIndexForItem(name);
        if (index == -1) return null;
        return inventory.remove(index);
    }

    private int getIndexForItem(String name) {
        for (int index = 0; index < inventory.size(); index++) {
            if (inventory.get(index).getName().equals(name)) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(String itemName) {
        return (getIndexForItem(itemName) != -1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void moveToRoom(String roomname) {
        Level.Room nextRoom = currentRoom.getNeighbor(roomname);
        if (nextRoom == null) {
            System.out.println("You can't go to " + roomname + ". Try again.");
        } else {
            currentRoom = nextRoom;
        }
    }

    public String getInvetoryString() {
        if (inventory.size() == 0) return "You are carrying no items.";

        String out = "Your items: ";
        for (Item i : inventory) {
            out += i.getName() + ", ";
        }
        return out.substring(0, out.length() - 2);
    }

    public void pickupItem(String itemName) {
        if ( !currentRoom.contains(itemName) ) {
            System.out.println(currentRoom.getName() + " doesn't have the " + itemName);
            return;
        }

        Item i = getCurrentRoom().removeItem(itemName);
        this.addItem(i);

        System.out.println("You picked up the " + itemName + "!");
    }

    public void drop(String itemName) {
        if ( !this.contains(itemName) ) {
            System.out.println("You don't have the " + itemName);
            return;
        }
        int index = getIndexForItem(itemName);
        Item i = inventory.remove(index);
        getCurrentRoom().addItem(i);

        System.out.println("You dropped up the " + itemName + "!");
    }
}
