import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private HashMap<String, Room> rooms;

    public Level() {
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
        private ItemContainer items;

        private Room(String name) {
            neighbors = new ArrayList<>();
            this.name = name;
            items = new ItemContainer();
        }

        public void add(Item i) {
            items.add(i);
        }

        public Item removeItem(String name) {
            return items.remove(name);
        }

        public boolean contains(String itemName) {
            return items.contains(itemName);
        }

        public String getItemNamesString() {
            return items.getItemNamesString();
        }

        public String getName() {
            return name;
        }

        public Room getNeighbor(String name) {
            for (Room n : neighbors) {
                if (n.getName().equals(name)) {
                    return n;
                }
            }
            return null;
        }

        private void addNeighbor(Room n) {
            neighbors.add(n);
        }

        public String getNeighborNames() {
            if (neighbors.size() == 0) return "";
            String names = "";
            for (int i = 0; i < neighbors.size() - 1; i++) {
                Room Room = neighbors.get(i);
                names += Room.getName() + ", ";
            }
            names += neighbors.get(neighbors.size() - 1).getName() + ".";

            return names;
        }

        public String toString() {
            return this.name;
        }

    }
}
