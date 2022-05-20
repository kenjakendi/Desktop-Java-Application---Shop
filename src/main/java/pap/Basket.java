package pap;

import lombok.Data;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Data
public class Basket {
    public static Map<Item, Integer> basket = new HashMap<>();
    static Warehouse warehouse;

    public Basket(){}

    public Basket(Warehouse warehouse){
        this.warehouse = warehouse;
    }

    public static void resetBasket(){
        if (!basket.isEmpty()){
            basket.clear();
        }
    }

    public Item findItemByName(String name){
        for (Item item :basket.keySet()){
            String itemName = item.getName();
            if (itemName.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item){
        if (warehouse.containItem(item) && warehouse.getQuantity(item)>0) {
            if (basket.containsKey(item)){
                Integer oldValue = basket.get(item);
                basket.replace(item, oldValue + 1);
            } else {
                basket.put(item,1);
            }
        }
    }

    public void addMoreItems(Item item, Integer quantity){
        if (quantity > warehouse.getQuantity(item)){
            quantity=warehouse.getQuantity(item);
        }
        for (int i=0; i < quantity; i++){
            addItem(item);
        }
    }

    public static void buy(){
        for (Item item : basket.keySet()){
            warehouse.removeMoreItems(item, basket.get(item));
        }
        resetBasket();
    }

    public void removeItem(Item item){
        if (containItem(item)){
            basket.remove(item);
        }
    }

    public static ArrayList<String> getItemsNameList(){
        ArrayList<String> nameList = new ArrayList<>();
        for (Item item :basket.keySet()){
            String name = item.getName();
            nameList.add(name);
        }
        return nameList;
    }

    public boolean containItem(Item item){
        return basket.containsKey(item);
    }

    public Integer getQuantity(Item item) {
        if (containItem(item)) {
            return basket.get(item);
        }
        return -1;
    }

}
