package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

import vendingmachine.utils.DBModel;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:45 am
 * @description: This is the Product class which is used to get the data from Product
 */
public class Product extends DBModel {

    private static JSONArray data;
    public static final String path = "src/main/resources/vendingmachine/data/product.json";
    private int itemCode;
    private String itemName;
    private double itemPrice;
    private String itemCategory;
    private int itemQuantity;

    public static JSONArray getData() {
        return data;
    }

    public static void setData(JSONArray data) {
        Product.data = data;
    }

    public JSONObject serialize() {
        JSONObject prod = new JSONObject();
        prod.put("item_code", this.itemCode);
        prod.put("item_name", this.itemName);
        prod.put("item_price", this.itemPrice);
        prod.put("item_category", this.itemCategory);
        prod.put("item_quantity", this.itemQuantity);
        return prod;
    }

    public Product(int itemCode, String itemName, double itemPrice, String itemCategory, int itemQuantity) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemQuantity = itemQuantity;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
}
