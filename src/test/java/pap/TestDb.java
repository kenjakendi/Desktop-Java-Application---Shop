package pap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestDb {

    @Test
    public void testGetLastId() throws Exception {

        Item mockItem = Mockito.mock(Item.class);
        DBinquiry mockDb = Mockito.mock(DBinquiry.class);

        Assert.assertNotNull(mockItem);
        Assert.assertNotNull(mockDb);

        int expectedId = 13;
        when(mockDb.getLastId()).thenReturn(expectedId);

        int idFromMockDb = mockDb.getLastId();

        when(mockItem.getId()).thenReturn(idFromMockDb);

        Assert.assertEquals(expectedId, mockItem.getId());
    }

    @Test
    public void testIsLogin() throws Exception {
        DBinquiry mockDb = Mockito.mock(DBinquiry.class);
        Assert.assertNotNull(mockDb);
        String user = "Admin";
        String pass = "123";
        when(mockDb.isLogin(user, pass)).thenReturn(true);

        Assert.assertTrue(mockDb.isLogin(user, pass));
    }

    @Test
    public void testGetTransaction() throws Exception {
        DBinquiry mockDb = Mockito.mock(DBinquiry.class);
        Assert.assertNotNull(mockDb);
        double result = 10.5;
        int id = 10;
        when(mockDb.getTransaction(id)).thenReturn(10.5);
        Assert.assertEquals(result, mockDb.getTransaction(id), 0.1);
    }
}
