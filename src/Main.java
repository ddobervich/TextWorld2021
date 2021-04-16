import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //build up a graph of connected Rooms.
        Level graph = new Level();

        initRooms(graph);
        initItems(graph);
        initEntities(graph);

        Player player = new Player("Wally");
        graph.setPlayer(player);
        player.setCurrentRoom(graph.getRoom("hall"));

        String response = "";
        Scanner in = new Scanner(System.in);
        displayCommands();
        System.out.println("-------------");

        do {
            // display room and connections, asks for next action
            System.out.println("You are currently in the " + player.getCurrentRoom());
            System.out.print("What do you want to do? > ");
            response = in.nextLine();
            response = response.trim();
            System.out.println("");

            String[] words = response.split(" ");
            String firstWord = words[0];

            if (firstWord.equals("pickup")) {
                if (words.length < 2) {
                    System.out.println("Please type an item name, or type \"look\" to see items in the room.");
                    continue;
                }
                String itemName = response.substring( response.indexOf(" ")+1);

                player.pickupItem(itemName);

            } else if (firstWord.equals("drop")) {
                if (words.length < 2) {
                    System.out.println("Please type an item name, or type \"inventory\" to see your items.");
                    continue;
                }

                String itemName = response.substring( response.indexOf(" ")+1);
                player.drop(itemName);

            } else if (firstWord.equals("inventory")) {
                System.out.println(player.getInvetoryString());
            } else if (firstWord.equals("go")) {
                if (words.length < 2) {
                    System.out.println("Please type a room name, or type \"look\" to see rooms.");
                    continue;
                }
                String roomname = words[1];

                player.moveToRoom(roomname);

            } else if (firstWord.equals("look")) {
                System.out.println("You can go to the: " + player.getCurrentRoom().getNeighborNames());
                System.out.println("Room has items: " + player.getCurrentRoom().getItemNamesString());
                System.out.println("Entities here: " + player.getCurrentRoom().getEntityNamesString());
            } else if (firstWord.equals("add")) {
                if (words.length < 3 || !words[1].equals("room")) {
                    System.out.println("Please use the following format: add room <roomname>.");
                    continue;
                }
                String newName = words[2];
                graph.addRoom(newName);
                graph.addUndirectedEdge(player.getCurrentRoom().getName(), newName);
            } else if (firstWord.equals("quit")) {
                System.out.println("-------------");
                break;
            } else {
                displayCommands();
            }

            graph.moveAllCreatures();

            System.out.println("-------------");

        } while (!response.equals("quit"));

        System.out.println("Successfully quit game.");
    }

    private static void initEntities(Level g) {
        Chicken c = new Chicken(g.getRoom("hall"));

    }

    private static void initItems(Level g) {
        Item lobster = new Item("lobster");
        Item redlobster = new Item("red lobster");
        Item tenFootPole = new Item("Ten foot pole");

        g.getRoom("hall").add(lobster);
        g.getRoom("hall").add(redlobster);
        g.getRoom("dungeon").add(tenFootPole);
    }

    private static void initRooms(Level g) {
        g.addRoom("hall");
        g.addRoom("closet");
        g.addRoom("dungeon");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
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
