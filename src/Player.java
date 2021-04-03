public class Player {
    private String name, description;
    private Level.Room currentRoom;

    public Player(String name) {
        this.name = name;
        this.description = "";
        this.currentRoom = null;
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
        if (nextRoom == null){
            System.out.println("You can't go to " + roomname + ". Try again.");
        } else {
            currentRoom = nextRoom;
        }
    }
}
