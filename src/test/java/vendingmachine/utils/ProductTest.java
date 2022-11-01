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


    @BeforeEach
    public void init() {
        this.product = new Product(1001, "Mineral Water", 12, "drinks", 7);
    }


    @Test
    public void getItemCodeTest() {
        assertEquals(1001, this.product.getItemCode());
    }

    @Test
    public void setItemCodeTest() {
        this.product.setItemCode(1100);
        assertEquals(1100, this.product.getItemCode());
    }

    @Test
    public void getItemNameTest() {
        assertEquals("Mineral Water", this.product.getItemName());
    }

    @Test
    public void setItemNameTest() {
        this.product.setItemName("Mineral Juice");
        assertEquals("Mineral Juice", this.product.getItemName());
    }

    @Test
    public void getItemPriceTest() {
        assertEquals(12, this.product.getItemPrice());
    }

    @Test
    public void setItemPriceTest() {
        this.product.setItemPrice(13);
        assertEquals(13, this.product.getItemPrice());
    }

    @Test
    public void getItemCategoryTest() {
        assertEquals("drinks", this.product.getItemCategory());
    }

    @Test
    public void setItemCategoryTest() {
        this.product.setItemCategory("candies");
        assertEquals("candies", this.product.getItemCategory());
    }

    @Test
    public void getItemQuantityTest() {
        assertEquals(7, this.product.getItemQuantity());
    }

    @Test
    public void setItemQuantityTest() {
        this.product.setItemQuantity(6);
        assertEquals(6, this.product.getItemQuantity());
    }

    @Test
    public void toStringTest(){
        assertEquals("1001", this.product.toString());
    }

    @Test
    public void updateStockTest(){
        this.product.updateStock();
        assertEquals(7, this.product.getItemQuantity());
    }

}