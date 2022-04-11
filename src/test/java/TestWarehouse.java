import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestWarehouse {

    @Test
    public void testGetItemById(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item("apple");
        apple.setId(1);
        items.put(apple, 2);
        Item peach = new Item("peach");
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
    public void testGetItemByIdNoItem(){
        Map<Item, Integer> items = new HashMap<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);
        Item foundItem = warehouse.getItemById(10);

        Assert.assertNull(foundItem);
    }

    @Test
    public void testAddItemNewItem(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Item apple = new Item("apple");
        expectedMap.put(apple, 1);

        warehouse.addItem(apple);

        Assert.assertEquals(warehouse.getItems(), expectedMap);
    }

    @Test
    public void testAddItemOldItem(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();
        Item apple = new Item("apple");
        expectedMap.put(apple, 2);
        items.put(apple, 1);
        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);
        warehouse.addItem(apple);

        Assert.assertEquals(warehouse.getItems(), expectedMap);
    }

    @Test
    public void testAddMoreItems(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Item apple = new Item("apple");
        expectedMap.put(apple, 4);

        warehouse.addMoreItems(apple, 4);

        Assert.assertEquals(expectedMap, warehouse.getItems());
    }

    @Test
    public void testRemoveItem(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();

        Item apple = new Item("apple");
        items.put(apple, 2);
        expectedMap.put(apple, 1);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        warehouse.removeItem(apple);

        Assert.assertEquals(expectedMap, warehouse.getItems());
    }

    @Test
    public void testRemoveItemNoItem(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();

        Item apple = new Item("apple");
        Item peach = new Item("peach");
        items.put(peach, 1);
        expectedMap.put(peach, 1);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        warehouse.removeItem(apple);

        Assert.assertEquals(expectedMap, warehouse.getItems());
    }

    @Test
    public void testRemoveMoreItems(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();

        Item apple = new Item("apple");
        items.put(apple, 5);
        expectedMap.put(apple, 1);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        warehouse.removeMoreItems(apple, 4);

        Assert.assertEquals(expectedMap, warehouse.getItems());
    }

    @Test
    public void testRemoveMoreItemsNoItem(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();

        Item apple = new Item("apple");
        Item peach = new Item("peach");
        items.put(apple, 5);
        expectedMap.put(apple, 5);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        warehouse.removeMoreItems(peach, 3);

        Assert.assertEquals(expectedMap, warehouse.getItems());
    }
}
