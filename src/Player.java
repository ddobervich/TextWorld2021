public class Player {
    private String name, description;
    private Level.Room currentRoom;
    private ItemContainer items;

    public Player(String name) {
        this.name = name;
        this.description = "";
        this.currentRoom = null;
        this.items = new ItemContainer();
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public Item removeItem(String name) {
        return items.remove(name);
    }

    public boolean contains(String itemName) {
        return items.contains(itemName);
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
        return items.getItemNamesString();
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

        Item i = items.remove(itemName);
        getCurrentRoom().add(i);

        System.out.println("You dropped up the " + itemName + "!");
    }
}
