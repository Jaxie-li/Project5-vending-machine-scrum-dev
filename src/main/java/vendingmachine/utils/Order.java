package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Order extends DBModel {

    private static JSONArray data;

    public static final String path = "src/main/resources/vendingmachine/data/order_history.json";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
    private int id;
    private String username;
    private String status;
    private String paymentMethod;
    private final ArrayList<Product> products = new ArrayList<>();
    private LocalDateTime startTime;

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
//
//        System.out.println(obj.get("start_time").toString());
//        System.out.println(obj.get("close_time").toString());
        try {
            startTime = LocalDateTime.parse(obj.get("start_time").toString(), formatter);
        } catch (DateTimeException e) {
            // FIXME: handle exception
            startTime = null;
        }
        for (Object o: (JSONArray) obj.get("products")) {
            JSONObject each = (JSONObject) o;
            products.add(new Product(each));
        }
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

    public void payByCash() {
        paymentMethod = "cash";
    }

    public void payByCard() {
        paymentMethod = "card";
    }

    public int getOrderTotal() {
        int total = 0;
        for(Product p: products) {
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
        Order.create(Order.read(path), serialise(), path);
    }

    @Override
    public JSONObject serialise() {
        JSONObject order = new JSONObject();

        order.put("id", this.id);
        order.put("username", this.username);
        order.put("status", this.status);
        order.put("payment_method", this.paymentMethod);
        order.put("start_time", this.startTime.format(formatter));
//        order.put("close_time", this.closeTime.format(formatter));

        JSONArray prods = new JSONArray();
        for (Product p: products) {
            prods.add(p.serialise());
        }

        order.put("products", prods);

        return order;
    }


    public String getUsername() {
        return this.username;
    }

    public void addOrder() {
        Order.create(Order.read(path), serialise(), path);
    }

    public int getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getLastFiveOrder(User user){
        ArrayList<ArrayList<String>> transactions = new ArrayList<>();
//        ArrayList<ArrayList<String>> item = new ArrayList<ArrayList<String>>();
//        ArrayList<ArrayList<String>> quantity = new ArrayList<ArrayList<String>>();
        String userName = user.getUsername();

        for(Object each:data){
            JSONObject temp = (JSONObject) each;
            String name = (String) temp.get("username");
            if(name.equals(userName)) {
                JSONArray productList = (JSONArray) ((JSONObject) each).get("products");
                ArrayList<String> transaction = new ArrayList<>();
                for(Object o:productList){
                    JSONObject eachItem = (JSONObject) o;
                    transaction.add(eachItem.get("itemName").toString() + " ,"+ eachItem.get("itemQuantity").toString());
                }
                transactions.add(transaction);
            }
        }
        String transactionsString ="";
        transactionsString += "Order history:\n";
        for (int i = transactions.size()-1; i > transactions.size()-6 && i >=0; i--){
            transactionsString += String.join(",", transactions.get(i));
            transactionsString += "\n";
        }
        System.out.print(transactionsString);
        return transactionsString;

    }

}