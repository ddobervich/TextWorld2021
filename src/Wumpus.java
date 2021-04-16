import java.util.List;

public class Wumpus extends GenericEntity {

    public Wumpus(Level.Room room) {
        super("the wumpus");
        this.description = "a quiet, retiring soul";

        setCurrentRoom(room);
    }

    @Override
    public void move() {
        Level.Room playerRoom = findAdjacentRoomWithPlayer();
        if (playerRoom == null) {
            // don't move if player isn't close
        } else if (playerRoom.equals(getRoom())) {
            moveRandomly();             // if we're in the player's room, get out of there!
        } else {
            if (getRoom().getNeighborRooms().size() == 1) return;

            // Find neighbor without the player
            Level.Room next;
            do {
                next = getRoom().getRandomNeighbor();
            } while (next.containsPlayer());

            // move there
            move(next);
        }
    }
}