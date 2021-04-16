public class Popstar extends GenericEntity {

    public Popstar(Level.Room room) {
        super("popstar");
        this.description = "strangely compelling; you can't look away";

        setCurrentRoom(room);
    }

    @Override
    public void move() {
        Level.Room playerRoom = findAdjacentRoomWithPlayer();
        if (playerRoom == null) {
            moveRandomly();
        } else {
            move(playerRoom);
        }
    }
}

