public class Chicken extends GenericEntity {

    public Chicken(Level.Room room) {
        super("chicken");
        this.description = "a chicken of distinction";
        this.currentRoom = room;
        this.currentRoom.add(this);
    }

    @Override
    public void move() {
        Level.Room next = this.currentRoom.getRandomNeighbor();
        if(next == null) return;

        this.currentRoom.removeEntity(this);    // remove self from old room
        this.currentRoom = next;                  // change what room
        this.currentRoom.add(this);               // add self to new room
    }
}