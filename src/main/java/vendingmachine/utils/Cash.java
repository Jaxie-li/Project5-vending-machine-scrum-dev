package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import vendingmachine.utils.DBModel;
import java.util.ArrayList;
import java.lang.Math;


/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:55 am
 * @description: This is the instance for the cash
 */
public class Cash extends DBModel {

    private static JSONArray data;
    public static final String path = "src/main/resources/vendingmachine/data/cash.json";
    private int value;
    private int quantity;

    public static JSONArray getData() {
        return data;
    }
    public static void setData(JSONArray data) {
        Cash.data = data;
    }

    public JSONObject serialise() {
        return new JSONObject();
    }

    public Cash(int value, int quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public Cash(JSONObject obj) {
        this.value =    Integer.parseInt(obj.get("value").toString());
        this.quantity = Integer.parseInt(obj.get("quantity").toString());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // testing purpose
    @Override
    public String toString() {
        return String.format("Value = %.2f, Quantity = %d", (double) this.value / 100, this.quantity);
    }


    public static boolean isSufficient(int price, int received) {
        return received >= price;
    }

    // Assume is sufficient
    public static ArrayList<Cash> payCash(int price, int received, ArrayList<Cash> vendingMachineCash) {

        assert received >= price;

        if (price == received) {
            return new ArrayList<>();
        }
        else {
            return exchange(price, received, vendingMachineCash);
        }

    }

    private static ArrayList<Cash> exchange(int price, int received, ArrayList<Cash> vendingMachineCash) {
        int exchange = received - price;

        ArrayList<Cash> cash = new ArrayList<>();

        int value[] = { 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5 };

        for (int v: value) {
            //System.out.println(exchange);
            // the quantity of value wanted
            int q = (int) Math.floor(exchange / v);

            // the actual quantity of value avaliable in the vending machine
            int actual = has(v, q, vendingMachineCash);

            if (actual > 0) {
                // reduce remaining exchange by the actual quantity avaliable in the vending machine by the value
                int amount = actual * v;
                exchange -= amount;

                // append cash instance
                cash.add(new Cash(v, actual));
            }

            // early return if vending machine has sufficient amount to exchange
            if (exchange == 0) {
                return cash;
            }
        }

        // return null if vending machine does not have sufficient amount to exchange
        return null;
    }

    private static int has(int v, int q, ArrayList<Cash> array) {
        for (Cash c: array) {
            if (c.getValue() == v) {
                return Math.min(c.getQuantity(), q);
            }
        }
        return -1;
    }
}
