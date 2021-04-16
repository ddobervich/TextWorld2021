import java.util.*;

public class Level {
    private HashMap<String, Room> rooms;
    private ArrayList<Entity> entities;
    private Player player;

    public Level() {
        this(null);
    }

    public Level(Player p) {
        rooms = new HashMap<String, Room>();
        entities = new ArrayList<>();
        this.player = player;
    }

    public void setPlayer(Player p){
        this.player = p;
    }

    public void addRoom(String name) {
        name = name.trim();
        rooms.put(name, new Room(name, this));
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

    public void moveAllCreatures() {
        for (Entity e : entities) {
            e.move();
        }
    }

    private int getIndexForEntity(String name) {
        for (int index = 0; index < entities.size(); index++) {
            if (entities.get(index).getName().equals(name)) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(Entity entity) {
        return entities.contains(entity);
    }

    public void add(Entity e) {
        entities.add(e);
    }

    public void createItem(String itemName, String roomName) {
        Item item = new Item(itemName);
        this.getRoom(roomName).add(item);
    }

    public void addRandomChickens(int num) {
        for (int i = 0; i < num; i++) {
            Chicken c = new Chicken(getRandomRoom(), "chicken "+ i);
        }
    }

    private Room getRandomRoom() {
        Set<String> roomList = rooms.keySet();
        if (roomList.size() == 0) return null;

        int r = (int)(Math.random()*roomList.size());
        return (Room) roomList.toArray()[r];
    }

    public void createWumpus(String roomName) {
        Room r = rooms.get(roomName);
        Wumpus w = new Wumpus(r);
    }

    public void createPopstar(String roomName) {
        Room r = rooms.get(roomName);
        Popstar p = new Popstar(r);
    }

    public class Room {
        private String name;
        private ArrayList<Room> neighbors;
        private ItemContainer items;
        private Level level;

        private Room(String name, Level level) {
            neighbors = new ArrayList<>();
            this.name = name;
            items = new ItemContainer();
            this.level = level;
        }

        public void add(Item i) {
            items.add(i);
        }

        public String getEntityNamesString() {
            List<Entity> entities = getEntitiesIn(this);
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

        public Level level() {
            return level;
        }

        public List<Room> getNeighborRooms() {
            return Collections.unmodifiableList(neighbors);
        }

        public boolean containsPlayer() {
            return (level.getPlayer().getCurrentRoom().equals(this));
        }
    }

    private Player getPlayer() {
        return player;
    }

    private List<Entity> getEntitiesIn(Room room) {
        ArrayList<Entity> entityList = new ArrayList<>();
        for (Entity e : entities)  {
            if (e.getRoom().equals(room)) {
                entityList.add(e);
            }
        }

        return entityList;
    }
}
