package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    private Product productJSON;

    private static JSONArray data;

    private String path = "src/test/resources/productTest.json";

    @BeforeEach
    public void init() {
        this.product = new Product(1001, "Mineral Water", 12, "drinks", 7);
    }

    @BeforeEach
    public void initJSON() throws IOException, ParseException {
        JSONArray result = (JSONArray) new JSONParser().parse(new FileReader(path));


    }
    @Test
    void getData() {
        JSONObject tempObj = new JSONObject();
        tempObj.put("item_code", 1001);
        tempObj.put("item_name", "Mineral Water");
        tempObj.put("item_price", 2.5);
        tempObj.put("item_category", "drinks");
        tempObj.put("item_quantity", 7);
        JSONArray tempArr = new JSONArray();
        tempArr.add(tempObj);
        Product.setData(tempArr);
//        Product tempProduct = new Product(tempObj);
        assertEquals(tempArr, Product.getData());
    }

    @Test
    void setData() {
    }


    @Test
    void getItemCode() {
        assertEquals(1001, this.product.getItemCode());
    }

    @Test
    void setItemCode() {
        this.product.setItemCode(1100);
        assertEquals(1100, this.product.getItemCode());
    }

    @Test
    void getItemName() {
        assertEquals("Mineral Water", this.product.getItemName());
    }

    @Test
    void setItemName() {
        this.product.setItemName("Mineral Juice");
        assertEquals("Mineral Juice", this.product.getItemName());
    }

    @Test
    void getItemPrice() {
        assertEquals(12, this.product.getItemPrice());
    }

    @Test
    void setItemPrice() {
        this.product.setItemPrice(13);
        assertEquals(13, this.product.getItemPrice());
    }

    @Test
    void getItemCategory() {
        assertEquals("drinks", this.product.getItemCategory());
    }

    @Test
    void setItemCategory() {
        this.product.setItemCategory("candies");
        assertEquals("candies", this.product.getItemCategory());
    }

    @Test
    void getItemQuantity() {
        assertEquals(7, this.product.getItemQuantity());
    }

    @Test
    void setItemQuantity() {
        this.product.setItemQuantity(6);
        assertEquals(6, this.product.getItemQuantity());
    }

}