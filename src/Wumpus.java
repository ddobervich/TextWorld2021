import java.util.List;

public class Wumpus extends GenericEntity {

    public Wumpus(Level.Room room) {
        super("the wumpus");
        this.description = "a quiet, retiring soul";

        setCurrentRoom(room);
    }

    @Override
    public void move() {
        Level.Room playerRoom = searchForPlayer();
        if (playerRoom == null) {
            move(getRoom().getRandomNeighbor());
        } else {
            // if only one room, it must have the player so don't do anything
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

    private Level.Room searchForPlayer() {
        for (Level.Room neighbor : getRoom().getNeighborRooms()) {
            if (neighbor.containsPlayer()) {
                return neighbor;
            }
        }
        return null;
    }

}