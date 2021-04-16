import java.util.ArrayList;

public class Chicken extends GenericEntity {

    public Chicken(Level.Room room) {
        super("chicken");
        this.description = "a chicken of distinction";

        setCurrentRoom(room);
    }

    public Chicken(Level.Room room, String name) {
        super(name);

        setCurrentRoom(room);
    }

    @Override
    public void move() {
        moveRandomly();
    }
}

