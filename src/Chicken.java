import java.util.ArrayList;

public class Chicken extends GenericEntity {

    public Chicken(Level.Room room) {
        super("chicken");
        this.description = "a chicken of distinction";

        setCurrentRoom(room);
    }

    @Override
    public void move() {
        Level.Room next = this.getRoom().getRandomNeighbor();
        move(next);     // run safe inherited move
    }
}

