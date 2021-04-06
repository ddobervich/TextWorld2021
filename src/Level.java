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
        private ArrayList<Entity> entities;
        private ItemContainer items;

        private Room(String name) {
            neighbors = new ArrayList<>();
            this.name = name;
            items = new ItemContainer();
            entities = new ArrayList<>();
        }

        public void add(Entity e) { entities.add(e); }
        public void add(Item i) {
            items.add(i);
        }

        public Entity removeEntity(String name) {
            name = name.trim();
            int index = getIndexForEntity(name);
            if (index == -1) return null;
            return entities.remove(index);
        }

        private int getIndexForEntity(String name) {
            for (int index = 0; index < entities.size(); index++) {
                if (entities.get(index).getName().equals(name)) {
                    return index;
                }
            }
            return -1;
        }

        public boolean containsEntity(String entityName) {
            return (getIndexForEntity(entityName) != -1);
        }

        public String getEntityNamesString() {
            if (entities.size() == 0) return "There's no one else here.";

            String out = "";
            for (Entity i : entities) {
                out += i.getName() + ", ";
            }
            return out.substring(0, out.length() - 2);
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

        public Room getRandomNeighbor() {
            if (neighbors.size() == 0) return null;
            int index = (int)(Math.random()*neighbors.size());
            return neighbors.get(index);
        }

        public void removeEntity(Entity e) {
            entities.remove(e);
        }
    }
}
