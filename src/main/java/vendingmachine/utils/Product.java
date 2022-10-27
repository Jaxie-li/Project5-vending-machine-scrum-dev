package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

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
    private int itemPrice;
    private String itemCategory;
    private int itemQuantity;

    public static JSONArray getData() {
        return data;
    }

    public static void setData(JSONArray data) {
        Product.data = data;
    }

    public JSONObject serialise() {
        JSONObject prod = new JSONObject();
        prod.put("itemCode", this.itemCode);
        prod.put("itemName", this.itemName);
        prod.put("itemPrice", this.itemPrice);
        prod.put("itemCategory", this.itemCategory);
        prod.put("itemQuantity", this.itemQuantity);
        return prod;
    }

    public Product(int itemCode, String itemName, int itemPrice, String itemCategory, int itemQuantity) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemQuantity = itemQuantity;
    }

    public Product(JSONObject obj) {
        this.itemCode = Integer.parseInt(obj.get("itemCode").toString());
        this.itemName = obj.get("itemName").toString();
        this.itemPrice = Integer.parseInt(obj.get("itemPrice").toString());
        this.itemCategory = obj.get("itemCategory").toString();
        this.itemQuantity = Integer.parseInt(obj.get("itemQuantity").toString());
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

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * update the stock based on the pk value
     */
    public void updateStock() {
        Product.update(read(Product.path), serialise(), path, "itemCode");
    }

    @Override
    public String toString() {
        return String.format("%s\t\t$%s", itemName, itemPrice);
    }
}