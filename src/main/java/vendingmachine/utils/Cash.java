package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import vendingmachine.utils.DBModel;


/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:55 am
 * @description: This is the instance for the cash
 */
public class Cash extends DBModel {

    private static JSONArray data;
    public static final String path = "src/main/resources/vendingmachine/data/cash.json";
    private String value;

    private int quantity;
    public static JSONArray getData() {
        return data;
    }
    public static void setData(JSONArray data) {
        Cash.data = data;
    }

    public JSONObject serialize() {
        return new JSONObject();
    }

    public Cash(String value, int quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
