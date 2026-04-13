package Basic.Interfaces;

import Entity.Items.Item;

import java.util.ArrayList;

public interface HaveInside {
    ArrayList<Item> getItems();
    void addItem(Item item) throws Exception;
    Item getItem(Item item) throws Exception;
    void setItems(ArrayList<Item> items) throws Exception;
}
