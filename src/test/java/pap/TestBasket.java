package pap;

import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class TestBasket {

    @Test
    public void testAddItem(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item("apple");
        items.put(apple, 2);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Basket basket = new Basket();
        basket.setWarehouse(warehouse);
        basket.addItem(apple);

        Map<Item, Integer> expectedItems = new HashMap<>();
        expectedItems.put(apple, 1);

        Assert.assertEquals(basket.getBasket(), expectedItems);
    }

    @Test
    public void testAddItemNoItemInWarehouse(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item("apple");
        items.put(apple, 0);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Basket basket = new Basket();
        basket.setWarehouse(warehouse);
        basket.addItem(apple);

        Assert.assertTrue(basket.getBasket().isEmpty());
    }

    @Test
    public void testAddMoreItems(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item("apple");
        items.put(apple, 3);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Basket basket = new Basket();
        basket.setWarehouse(warehouse);
        basket.addMoreItems(apple, 3);

        Map<Item, Integer> expectedItems = new HashMap<>();
        expectedItems.put(apple, 3);

        Assert.assertEquals(basket.getBasket(), expectedItems);
    }

    @Test
    public void testAddMoreItemsNoEnoughItemInWarehouse(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item("apple");
        items.put(apple, 2);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Basket basket = new Basket();
        basket.setWarehouse(warehouse);
        basket.addMoreItems(apple, 10);

        Map<Item, Integer> expectedItems = new HashMap<>();
        expectedItems.put(apple, 2);

        Assert.assertEquals(basket.getBasket(), expectedItems);
    }

    @Test
    public void testResetBasket(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item("apple");
        items.put(apple, 2);
        Item peach = new Item("peach");
        items.put(peach, 4);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Basket basket = new Basket();
        basket.setWarehouse(warehouse);
        basket.addMoreItems(apple, 2);
        basket.addMoreItems(peach, 3);

        Assert.assertFalse(basket.getBasket().isEmpty());

        basket.resetBasket();

        Assert.assertTrue(basket.getBasket().isEmpty());
    }

    @Test
    public void testBuy(){
        Map<Item, Integer> items = new HashMap<>();
        Item apple = new Item("apple");
        items.put(apple, 2);
        Item peach = new Item("peach");
        items.put(peach, 4);

        Warehouse warehouse = new Warehouse();
        warehouse.setItems(items);

        Basket basket = new Basket();
        basket.setWarehouse(warehouse);
        basket.addMoreItems(apple, 2);
        basket.addMoreItems(peach, 3);

        basket.buy();

        Map<Item, Integer> expectedItems = new HashMap<>();
        expectedItems.put(apple, 0);
        expectedItems.put(peach, 1);

        Assert.assertEquals(warehouse.getItems(), expectedItems);
        Assert.assertTrue(basket.getBasket().isEmpty());
    }
}
