package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order extends DBModel {

    private static JSONArray data;

    public static final String path = "src/main/resources/vendingmachine/data/order_history.json";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private int id;
    private String username;
    private String status;
    private String paymentMethod;
    private final ArrayList<Product> products = new ArrayList<>();
    private LocalDateTime startTime;

    private int exchange;
    private int paid;

    public Order(User user) {
        id = getNextId();
        username = user != null ? user.getUsername() : "";
        status = "open";
        startTime = LocalDateTime.now();
    }

    public Order(JSONObject obj) {
        id = Integer.parseInt(obj.get("id").toString());
        username = obj.get("username").toString();
        status = obj.get("status").toString();
        paymentMethod = obj.get("paymentMethod").toString();
        startTime = LocalDateTime.parse(obj.get("startTime").toString(), formatter);
        exchange = Integer.parseInt(obj.get("exchange").toString());
        paid = Integer.parseInt(obj.get("paid").toString());
        for (Object o : (JSONArray) obj.get("products")) {
            JSONObject each = (JSONObject) o;
            products.add(new Product(each));
        }
    }

    public void addOrder() {
        data = Order.create(data, serialise(), path);
    }

    public String getUsername() {
        return username;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getExchange() {
        return exchange;
    }

    public void setExchange(ArrayList<Cash> cashes) {
        int exc = 0;
        for (Cash c: cashes) {
            exc += c.getQuantity() * c.getValue();
        }
        this.exchange = exc;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    private static int getNextId() {
        return data.toArray().length + 1;
    }

    public static JSONArray getData() {
        return data;
    }

    public static void setData(JSONArray data) {
        Order.data = data;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderTotal() {
        int total = 0;
        for (Product p : products) {
            total += p.getItemPrice() * p.getItemQuantity();
        }
        return total;
    }

    public void cancelByTimeOut() {
        status = "time out";
        Order.create(data, serialise(), path);
    }

    public void cancelByUser() {
        status = "user cancel";
        Order.create(data, serialise(), path);
    }

    public void cancelByNoExchange() {
        status = "no exchange";
        Order.create(data, serialise(), path);
    }

    public void finalizeOrder() {
        Order.create(data, serialise(), path);
    }

    @Override
    public JSONObject serialise() {
        JSONObject order = new JSONObject();

        order.put("id", this.id);
        order.put("username", this.username);
        order.put("status", this.status);
        order.put("payment_method", this.paymentMethod);
        order.put("start_time", this.startTime.format(formatter));
        order.put("exchange", this.exchange);
        order.put("paid", this.paid);

        JSONArray prods = new JSONArray();
        for (Product p : products) {
            prods.add(p.serialise());
        }

        order.put("products", prods);

        return order;
    }
}