package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CashTest {

    private static final String path = "src/test/resources/cashTest.json";

    private static JSONArray data;
//    private static DBModel dbModel = mock(DBModel.class);


    private static Cash cash;

    @BeforeAll
    public static void init() {
        cash = new Cash(50, 2);
        MockedStatic<DBModel> dbModel = mockStatic(DBModel.class);
        dbModel.when(()->DBModel.read(anyString())).thenReturn(null);
    }

//    @Test
//    public void readTest(){
//        assertNull(DBModel.read("123"));
//    }

    @Test
    public void constructorTest() {
        assertNotNull(cash);
    }

//    @Test
//    void getData() {
//        JSONObject test = new JSONObject();
//        JSONArray result = new JSONArray();
//        result.add("value: 100.0");
//        result.add("quantity: 5");
//        test.put(result);
//        assertNull(DBModel.read("123"));
//        assertEquals(result, DBModel.read(path));
//    }

//    @Test
//    void setData() {
//        // JSON
//    }

    @Test
    void getValue() {
        assertEquals(100, cash.getValue());
    }

    @Test
    void setValue() {
        cash.setValue(100);
        assertEquals(100, cash.getValue());
    }

//    @Test
//    void getQuantity() {
//        assertEquals(2, this.cash.getValue());
//    }

    @Test
    void setQuantity() {
        cash.setQuantity(3);
        assertEquals(3, cash.getQuantity());
    }

    @Test
    void testToString() {
        assertEquals("Value = 0.50, Quantity = 2", cash.toString());
    }

    @Test
    void isSufficient() {
        assertTrue(Cash.isSufficient(12, 12));
        assertTrue(Cash.isSufficient(12, 13));
        assertFalse(Cash.isSufficient(12, 11));
    }

    @Test
    void payCash() {
        // include exchange()
        ArrayList<Cash> cashes = new ArrayList<>();
        cashes.add(new Cash(10,2));
        assertEquals("Value = 0.10, Quantity = 1",cash.payCash(20,30,cashes).get(0).toString());

    }


//    @Test
//    void exchange(){
//        ArrayList<String> cashTest = new ArrayList<>();
//        cashTest.add("Value = 50.00, Quantity = 1");
//        assertEquals(["Value = 50.00, Quantity = 1","Value = 10.00, Quantity = 1", "Value = 5.00, Quantity = 1", "Value = 1.00, Quantity = 1","Value = 0.50, Quantity = 1"], this.cash.exchange)
//
//    }

    @Test
    void toStringTest(){
        assertEquals("Value = 0.50, Quantity = 2", cash.toString());
    }

}