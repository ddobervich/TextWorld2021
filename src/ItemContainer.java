import java.util.ArrayList;

public class ItemContainer {
    private ArrayList<Item> inventory;

    public ItemContainer() {
        inventory = new ArrayList<>();
    }

    public void add(Item i) {
        inventory.add(i);
    }

    public Item remove(String name) {
        name = name.trim();
        int index = getIndexForItem(name);
        if (index == -1) return null;
        return inventory.remove(index);
    }

    private int getIndexForItem(String name) {
        for (int index = 0; index < inventory.size(); index++) {
            if (inventory.get(index).getName().equals(name)) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(String itemName) {
        return (getIndexForItem(itemName) != -1);
    }

    public String getItemNamesString() {
        if (inventory.size() == 0) return "Room has no items.";

        String out = "";
        for (Item i : inventory) {
            out += i.getName() + ", ";
        }
        return out.substring(0, out.length() - 2);
    }

}
