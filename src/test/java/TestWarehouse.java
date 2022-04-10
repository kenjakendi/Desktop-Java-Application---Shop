import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestWarehouse {

    @Test
    public void testGetItemById(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item();
        apple.setId(1);
        items.put(apple, 2);
        Item peach = new Item();
        peach.setId(2);
        items.put(peach, 4);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Item foundApple = warehouse.getItemById(1);
        Item foundPeach = warehouse.getItemById(2);

        Assert.assertEquals(apple, foundApple);
        Assert.assertEquals(peach, foundPeach);
    }

    @Test
    public void testAddItem(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> appleMap = new HashMap<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Item apple = new Item();
        appleMap.put(apple, 1);

        warehouse.addItem(apple);

        Assert.assertEquals(warehouse.getItems(), appleMap);
    }

    @Test
    public void testAddMoreItems(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> appleMap = new HashMap<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Item apple = new Item();
        appleMap.put(apple, 4);

        warehouse.addMoreItems(apple, 4);

        Assert.assertEquals(warehouse.getItems(), appleMap);
    }
}
