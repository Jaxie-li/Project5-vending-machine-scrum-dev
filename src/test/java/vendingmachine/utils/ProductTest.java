package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    public void init() {
        this.product = new Product(1001, "Mineral Water", 12.0, "drinks", 7);
    }

    @Test
    void getData() {

    }

    @Test
    void setData() {
    }

    @Test
    void serialize() {
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
        assertEquals(12.0, this.product.getItemPrice());
    }

    @Test
    void setItemPrice() {
        this.product.setItemPrice(13.0);
        assertEquals(13.0, this.product.getItemPrice());
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