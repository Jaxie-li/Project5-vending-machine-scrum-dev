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

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id;
    private String username;
    private String status;
    private String paymentMethod;
    private final ArrayList<Product> products = new ArrayList<>();
    private LocalDateTime startTime;
    private LocalDateTime closeTime;

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
        paymentMethod = obj.get("payment_method").toString();
        startTime = LocalDateTime.parse(obj.get("start_time").toString(), formatter);
        try {
            closeTime = LocalDateTime.parse(obj.get("close_time").toString(), formatter);
        } catch (DateTimeException e) {
            closeTime = null;
        }
        for (Object o: (JSONArray) obj.get("products")) {
            JSONObject each = (JSONObject) o;
            products.add(new Product(each));
        }
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

    public void payByCash() {
        paymentMethod = "cash";
    }

    public void payByCard() {
        paymentMethod = "card";
    }

    public double getOrderTotal() {
        double total = 0.0;
        for(Product p: products) {
            total += p.getItemPrice() * p.getItemQuantity();
        }
        return total;
    }

    public void cancelByTimeOut() {
        status = "time out";
        Order.create(data, serialize(), path);
    }

    public void cancelByUser() {
        status = "user cancel";
        Order.create(data, serialize(), path);
    }

    public void cancelByNoExchange() {
        status = "no exchange";
        Order.create(data, serialize(), path);
    }

    public void finalizeOrder() {
        Order.create(data, serialize(), path);
    }

    @Override
    public JSONObject serialize() {
        JSONObject order = new JSONObject();

        order.put("id", this.id);
        order.put("username", this.username);
        order.put("status", this.status);
        order.put("payment_method", this.paymentMethod);
        order.put("start_time", this.startTime.format(formatter));
        order.put("close_time", this.closeTime.format(formatter));

        JSONArray prods = new JSONArray();
        for (Product p: products) {
            prods.add(p.serialize());
        }

        order.put("products", prods);

        return order;
    }
}