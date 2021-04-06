public abstract class GenericEntity implements Entity {
    protected String name, description;
    protected Level.Room currentRoom;

    public GenericEntity(String name) {
        this.name = name;
        this.description = "";
        this.currentRoom = null;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public abstract void move();
}
