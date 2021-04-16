public abstract class GenericEntity implements Entity {
    protected String name, description;
    private Level.Room currentRoom;

    public GenericEntity(String name) {
        this.name = name;
        this.description = "";
        this.currentRoom = null;
    }

    @Override
    public abstract void move();

    protected Level.Room findAdjacentRoomWithPlayer() {
        for (Level.Room neighbor : getRoom().getNeighborRooms()) {
            if (neighbor.containsPlayer()) {
                return neighbor;
            }
        }
        return null;
    }

    protected void moveRandomly() {
        Level.Room next = getRoom().getRandomNeighbor();
        if (next != null) {
            move(next);
        }
    }

    protected void move(Level.Room nextRoom) {
        if (nextRoom == null) return;
        this.currentRoom = nextRoom;
    }

    protected void setCurrentRoom(Level.Room room) {
        if (!room.level().contains(this)) {
            room.level().add(this);
        }
        this.currentRoom = room;                     // set the room for the creature
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Level.Room getRoom() {
        return this.currentRoom;
    }

}
