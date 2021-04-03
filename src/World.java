import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {
    public static final String[] commandWords = {"go", "look", "pickup"};
    private HashMap<String, Room> rooms;

    public World(){
        rooms = new HashMap<String, Room>();
    }

    public void addRoom(String name) {
        rooms.put(name, new Room(name));
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        if (n1 == null || n2 == null) return;

        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
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

        private Room(String name) {
            neighbors = new ArrayList<>();
            this.name = name;
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

    }
}
