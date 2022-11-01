package vendingmachine.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vendingmachine.model.VendingMachineModel;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    private User customer;

    private User seller;

    private ArrayList<Product> products = new ArrayList<>();



    @BeforeEach
    public void init() throws IOException, ParseException {
        this.customer = new User("testCustomer1", "123456", "customer");
        this.seller = new User("testSeller", "testseller","seller");
        this.order = new Order(customer);
        products.add(new Product(1001, "Mineral Water", 250, "drinks", 4));
    }

    @Test
    public void constructorTest() {
        assertNotNull(order);
    }

    @Test
    public void constructorTest2(){
        JSONObject order = new JSONObject();
        order.put("id",1);
        order.put("username",1);
        order.put("status",1);
        order.put("paymentMethod",1);
        order.put("startTime","06-03-2007 13:29:45");
        order.put("exchange",1);
        order.put("paid",1);
        assertThrows(NullPointerException.class,()->new Order(order));
    }


    @Test
    public void getUsernameTest(){
        assertEquals("testCustomer1", customer.getUsername());
        assertNotEquals("testCustomer1",seller.getUsername());
        assertEquals("testCustomer1",order.getUsername());
    }

    @Test
    public void serialiseTest(){
        assertNotNull(order.serialise());
    }


    @Test
    void addProductTest() {
        products.add(new Product(1002, "Sprite", 300, "drinks", 7));
        assertNotNull(order.getProducts());
    }
    
    @Test
    public void addOrderTest(){
        assertDoesNotThrow(()->{order.addOrder();});
    }
    
    @Test
    public void setPaymentTest(){
        assertDoesNotThrow(()->{order.setPaymentMethod("1");});
        assertEquals("1",order.getPaymentMethod());
    }

    

    @Test
    void payByCard() {
    }

    @Test
    void getOrderTotalTest() {
        // Fetch data form database
//        Order.setData(Order.read(Order.path));
//        Order.getData();
//        Order sellerOrder = new Order(seller);
//        assertNull(sellerOrder.getOrderTotal());
        assertNotNull(order.getOrderTotal());
    }

    @Test
    public void cancelByTimeOutTest() {
        order.cancelByTimeOut();
        assertEquals("time out", order.getStatus());
    }

    @Test
    public void cancelByUserTest() {
        order.cancelByUser();
        assertEquals("user cancel", order.getStatus());
    }

    @Test
    public void cancelByNoExchangeText() {
        order.cancelByNoExchange();
        assertEquals("no exchange", order.getStatus());
    }


    @Test
    public void getIdTest(){
        assertNotNull(order.getId());
    }

    @Test
    public void getLastFiveOrderTest() {
//        Order.setData(Order.read(Order.path));
        assertNotNull(order.getLastFiveOrder(customer));
    }

    @Test
    public void getPaidTest(){
        assertNotNull(order.getPaid());
        order.setPaid(99);
        assertEquals(99, order.getPaid());
    }

    @Test
    public void getExchangeTest(){
        assertNotNull(order.getExchange());
        ArrayList<Cash> cashList = new ArrayList<>();
        Cash fiftyDollar = new Cash(50, 2);
        Cash tenDollar = new Cash(10, 2);
        cashList.add(fiftyDollar);
        cashList.add(tenDollar);
        order.setExchange(cashList);
        assertEquals(120, order.getExchange());
    }

    @Test
    public void getStartTimeTest(){
        assertNotNull(order.getStartTime());
    }

}