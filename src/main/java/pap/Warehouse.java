package pap;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Warehouse {
    @Getter @Setter
    static Map<Item, Integer> items;
    @Getter @Setter
    static HashSet<Integer> id_set = new HashSet<Integer>();
    @Getter @Setter
    public static int LASTid;

    public Warehouse(Map<Item, Integer> items){
        this.items = items;
        for (Item item : items.keySet()){
            id_set.add(item.getId());
        }
    }

    public Warehouse(){}

    public Item getItemById(int id){
        for (Item item :items.keySet()){
            int itemId = item.getId();
            if (itemId == id) {
                return item;
            }
        }
        return null;
    }
    public void dropAllitemsToBase() throws Exception {
        DBinquiry db = new DBinquiry();
        for (Item item :items.keySet()) {

            db.dropToBase(item, items.get(item));
        }
    }

    public void addItem(Item item){
        if (containItem(item)){
            Integer oldValue = getQuantity(item);
            items.replace(item, oldValue + 1);
        } else{
            items.put(item, 1);
        }
    }

    public void addMoreItems(Item item, Integer quantity){
        for (int i=0; i < quantity; i++){
            addItem(item);
        }
    }

    public void removeItem(Item item){
        if (containItem(item) && getQuantity(item)>0){
            Integer oldValue = getQuantity(item);
            items.replace(item, oldValue - 1);
        }
    }

    public void removeMoreItems(Item item, Integer quantity){
        for (int i=0; i < quantity; i++){
            removeItem(item);
        }
    }

    public Integer getQuantity(Item item) {
        if (containItem(item)) {
            return items.get(item);
        }
        return -1;
    }

    public static ArrayList<String> getItemsNameList(){
        ArrayList<String> nameList = new ArrayList<>();
        for (Item item :items.keySet()){
            String name = item.getName();
            nameList.add(name);
        }
        return nameList;
    }

    public void clearItems(){
        items.clear();
        id_set.clear();
    }

    public void addMapOfItems(Map<Item, Integer> itemsToAdd){
        for (Item item : itemsToAdd.keySet()){
            addMoreItems(item, itemsToAdd.get(item));
        }
    }

    public void completeRemoveItem(Item item){
        id_set.remove(item.getId());
        items.remove(item);
    }

    public boolean containItem(Item item){
        return items.containsKey(item); }

    public static Item convertItem(SupplierItem supItem){
        for (Item item : items.keySet()){
            if (item.getName().equals(supItem.getName())){
                return item;
            }
        }
        Item item = new Item(supItem.getName());
        item.setId(Collections.max(id_set) + 1);
        id_set.add(item.getId());
        return item;
    }
}
