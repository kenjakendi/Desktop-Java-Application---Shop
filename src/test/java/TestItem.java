import org.junit.Test;
import org.junit.Assert;

public class TestItem {

    @Test
    public void testId(){
        Item item = new Item();
        int id = 10;
        item.setId(id);
        Assert.assertEquals(item.getId(), id);
    }

    @Test
    public void testName(){
        Item item = new Item();
        String name = "Apple";
        item.setName(name);
        Assert.assertEquals(item.getName(), name);
    }
}
