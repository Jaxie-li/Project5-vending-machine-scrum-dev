package vendingmachine.model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vendingmachine.utils.Cash;
import vendingmachine.utils.Product;
import vendingmachine.utils.User;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineModelTest {

    private VendingMachineModel model;

    private User admin = new User("admin", "123456", "owner");

    private Product minerWater = new Product(1001, "Mineral Water", 250, "drinks", 7);

    private Cash fiftyDollar = new Cash(50, 1);

    @BeforeEach
    public void init() throws IOException, ParseException {
        this.model = new VendingMachineModel();
    }

    @Test
    void getCurrentUser() {
        assertNull(model.getCurrentUser());
        model.setCurrentUser(admin);
        assertNotNull(model.getCurrentUser());
    }

    @Test
    void setCurrentUser() {
        model.setCurrentUser(admin);
        assertEquals(admin, model.getCurrentUser());
    }

    @Test
    void getProductsTest() {
        assertNotNull(model.getProducts());
        ArrayList<Product> productListTest = new ArrayList<>();
        productListTest.add(minerWater);
        model.setProducts(productListTest);
        assertEquals("[1001]", model.getProducts().toString());
    }

    @Test
    void getCashesTest() {
        assertNotNull(model.getCashes());
        ArrayList<Cash> cashListTest = new ArrayList<>();
        cashListTest.add(fiftyDollar);
        model.setCashes(cashListTest);
        assertEquals("[Value = 0.50, Quantity = 1]", model.getCashes().toString());
    }

}