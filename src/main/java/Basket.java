import lombok.Data;
import java.util.Map;

@Data
public class Basket {
    Map<Item, Integer> basket;
    Warehouse warehouse;

    public void resetBasket(){
        if (!basket.isEmpty()){
            basket.clear();
        }
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
        for (int i=0; i < quantity; i++){
            addItem(item);
        }
    }

    public void buy(){
        for (Item item : basket.keySet()){
            warehouse.removeMoreItems(item, basket.get(item));
        }
        resetBasket();
    }

}
