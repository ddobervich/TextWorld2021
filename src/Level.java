import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private HashMap<String, Room> rooms;

    public Level(){
        rooms = new HashMap<String, Room>();
    }

    public void addRoom(String name) {
        name = name.trim();
        rooms.put(name, new Room(name));
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1.trim());
        Room n2 = getRoom(name2.trim());
        if (n1 == null || n2 == null) return;

        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1.trim());
        Room n2 = getRoom(name2.trim());
        if (n1 == null || n2 == null) return;

        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public class Room {
        private String name;
        private ArrayList<Room> neighbors;
        private ArrayList<Item> inventory;

        private Room(String name) {
            neighbors = new ArrayList<>();
            this.name = name;
            inventory = new ArrayList<>();
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

        public String getInvetoryString() {
            if (inventory.size() == 0) return "Room has no items.";

            String out = "";
            for (Item i : inventory) {
                out += i.getName() + ", ";
            }
            return out.substring(0, out.length() - 2);
        }

        public String getName(){
            return name;
        }

        public Room getNeighbor(String name){
            for (Room n: neighbors) {
                if (n.getName().equals(name)){
                    return n;
                }
            }
            return null;
        }

        private void addNeighbor(Room n){
            neighbors.add(n);
        }

        public String getNeighborNames(){
            if (neighbors.size() == 0) return "";
            String names = "";
            for (int i = 0; i < neighbors.size()-1; i++) {
                Room Room =  neighbors.get(i);
                names += Room.getName() + ", ";
            }
            names += neighbors.get(neighbors.size()-1).getName() + ".";

            return names;
        }

        public String toString() {
            return this.name;
        }

    }
}
