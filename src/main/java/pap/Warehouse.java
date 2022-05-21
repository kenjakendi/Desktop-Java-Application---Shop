package pap;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

public class Warehouse {
    @Getter @Setter
    static Map<Item, Integer> items;

    public Warehouse(Map<Item, Integer> items){
        this.items = items;
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

    public Item findItemByName(String name){
        for (Item item :items.keySet()){
            String itemName = item.getName();
            if (itemName.equals(name)) {
                return item;
            }
        }
        return null;
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
    }


    public boolean containItem(Item item){
        return items.containsKey(item);
    }
}
