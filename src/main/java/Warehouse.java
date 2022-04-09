import java.util.Map;

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
        if (items.containsKey(item)){
            Integer oldValue = items.get(item);
            items.replace(item, oldValue + 1);
        } else{
            items.put(item, 1);
        }
    }

    public void addMoreItem(Item item, Integer quantity){
        for (int i=0; i < quantity; i++){
            addItem(item);
        }
    }
}
