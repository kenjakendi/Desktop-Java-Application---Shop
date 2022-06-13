package pap;

import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestItem {

    @Test
    public void testMock(){
        Item mockItem = Mockito.mock(Item.class);
        Assert.assertNotNull(mockItem);
        when(mockItem.getName()).thenReturn("mock");
        String expectedName = "mock";
        Assert.assertEquals(expectedName, mockItem.getName());
    }

    @Test
    public void testId(){
        Item item = new Item("item");
        int id = 10;
        item.setId(id);
        Assert.assertEquals(item.getId(), id);
    }

    @Test
    public void testName(){
        Item item = new Item("item");
        String name = "Apple";
        item.setName(name);
        Assert.assertEquals(item.getName(), name);
    }
}
