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
    public void getUsernameTest(){
        assertEquals("testCustomer1", customer.getUsername());
        assertNotEquals("testCustomer1",seller.getUsername());
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
}