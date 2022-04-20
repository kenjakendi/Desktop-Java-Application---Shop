package pap;

import lombok.Data;
import java.util.Map;

@Data
public class Warehouse {
    Map<Item, Integer> items;

    public Item getItemById(int id){
        for (Item item :items.keySet()){
            int itemId = item.getId();
            if (itemId == id) {
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

    public boolean containItem(Item item){
        return items.containsKey(item);
    }
}
