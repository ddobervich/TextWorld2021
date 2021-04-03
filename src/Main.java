import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //build up a graph of connected Rooms.
        Level g = new Level();
        Player p = new Player("Wally");

        g.addRoom("hall");
        g.addRoom("closet");
        g.addRoom("dungeon");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        p.setCurrentRoom(g.getRoom("hall"));

        String response = "";
        Scanner in = new Scanner(System.in);
        displayCommands();
        System.out.println("-------------");

        do {
            // display room and connections, asks for next action
            System.out.println("You are currently in the " + p.getCurrentRoom());
            System.out.print("What do you want to do? > ");
            response = in.nextLine();
            response = response.trim();
            System.out.println("");

            String[] words = response.split(" ");
            String firstWord = words[0];

            if (firstWord.equals("go")){
                if (words.length < 2) {
                    System.out.println("Please type a room name, or type \"look\" to see rooms.");
                    continue;
                }
                String roomname = words[1];

                p.moveToRoom(roomname);

            } else if (firstWord.equals("look")){
                System.out.println("You can go to the: " + p.getCurrentRoom().getNeighborNames());
            } else if (firstWord.equals("add")){
                if (words.length < 3 || !words[1].equals("room")) {
                    System.out.println("Please use the following format: add room <roomname>.");
                    continue;
                }
                String newName = words[2];
                g.addRoom(newName);
                g.addUndirectedEdge(p.getCurrentRoom().getName(), newName);
            } else if (firstWord.equals("quit")) {
                System.out.println("-------------");
                break;
            } else {
                displayCommands();
            }

            System.out.println("-------------");
        }  while (!response.equals("quit"));

        System.out.println("Successfully quit game.");
    }

    private static void displayCommands() {
        System.out.println("Possible commands:");
        System.out.println("go <roomname> \t\t|| goes to specified room");
        System.out.println("look \t\t\t\t|| displays neighboring rooms  ");
        System.out.println("add room <roomname> || adds specified room with a 2-way connection to current room");
        System.out.println("quit \t\t\t\t|| quits game");
        System.out.println("help \t\t\t\t|| displays commands");
    }
}
