package pap;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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

        Warehouse warehouse = new Warehouse(items);

        Item foundApple = warehouse.getItemById(1);
        Item foundPeach = warehouse.getItemById(2);

        Assert.assertEquals(apple, foundApple);
        Assert.assertEquals(peach, foundPeach);
    }

    @Test
    public void testGetItemByIdNoItem(){
        Map<Item, Integer> items = new HashMap<>();
        Warehouse warehouse = new Warehouse(items);
        Item foundItem = warehouse.getItemById(10);

        Assert.assertNull(foundItem);
    }

    @Test
    public void testAddItemNewItem(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();
        Warehouse warehouse = new Warehouse(items);

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
        Warehouse warehouse = new Warehouse(items);
        warehouse.addItem(apple);

        Assert.assertEquals(warehouse.getItems(), expectedMap);
    }

    @Test
    public void testAddMoreItems(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();
        Warehouse warehouse = new Warehouse(items);

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

        Warehouse warehouse = new Warehouse(items);

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

        Warehouse warehouse = new Warehouse(items);

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

        Warehouse warehouse = new Warehouse(items);

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

        Warehouse warehouse = new Warehouse(items);
        warehouse.removeMoreItems(peach, 3);

        Assert.assertEquals(expectedMap, warehouse.getItems());
    }

    @Test
    public void testGetItemsNameList(){
        Map<Item, Integer> items = new HashMap<>();
        ArrayList<String> itemsNameList= new ArrayList<>();
        itemsNameList.add("peach");
        itemsNameList.add("apple");
        Item apple = new Item("apple");
        Item peach = new Item("peach");
        items.put(apple, 1);
        items.put(peach, 1);

        Warehouse warehouse = new Warehouse(items);

        Assert.assertEquals(itemsNameList, warehouse.getItemsNameList());
    }

    @Test
    public void testClearItems(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedMap = new HashMap<>();
        Item apple = new Item("apple");
        Item peach = new Item("peach");
        items.put(apple, 1);
        items.put(peach, 1);

        Warehouse warehouse = new Warehouse(items);
        warehouse.clearItems();

        Assert.assertEquals(expectedMap, warehouse.getItems());
    }

    @Test
    public void testAddMapOfItems(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> itemsToAdd = new HashMap<>();
        Map<Item, Integer> expectedItems = new HashMap<>();

        Item apple = new Item("apple");
        apple.setId(1);
        items.put(apple, 2);

        Item peach = new Item("peach");
        peach.setId(2);
        itemsToAdd.put(peach, 4);
        itemsToAdd.put(apple, 6);

        expectedItems.put(apple,8);
        expectedItems.put(peach,4);

        Warehouse warehouse = new Warehouse(items);
        warehouse.addMapOfItems(itemsToAdd);

        Assert.assertEquals(expectedItems, warehouse.getItems());
    }

    @Test
    public void testCompleteItems(){
        Map<Item, Integer> items = new HashMap<>();
        Map<Item, Integer> expectedItems = new HashMap<>();
        Item apple = new Item("apple");
        Item peach = new Item("peach");
        items.put(apple, 100);
        items.put(peach, 10);
        expectedItems.put(peach,10);

        Warehouse warehouse = new Warehouse(items);
        warehouse.completeRemoveItem(apple);

        Assert.assertEquals(expectedItems, warehouse.getItems());
    }

}
