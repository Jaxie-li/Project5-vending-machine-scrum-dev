package vendingmachine.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import vendingmachine.utils.Cash;
import vendingmachine.utils.Product;
import vendingmachine.utils.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:51 am
 * @description: This is the model for the VendingMachine, the responsibility of this class is to store the data.
 * Please Store all data in this model
 */
public class VendingMachineModel {
    private User currentUser;
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Cash> cashes = new ArrayList<>();
    public VendingMachineModel() throws IOException, ParseException {
        // Read all the product from database
        User.setData(User.read(User.path));

        // Read all the product from database
        Product.setData(Product.read(Product.path));

        for (Object o: Product.getData()) {
            JSONObject each = (JSONObject) o;
            products.add(new Product(each));
        }

        // Read all the cash from database
        Cash.setData(Cash.read(Cash.path));

        for (Object o: Cash.getData()) {
            JSONObject each = (JSONObject) o;
            cashes.add(new Cash(each));
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Cash> getCashes() {
        return cashes;
    }

    public void setCashes(ArrayList<Cash> cashes) {
        this.cashes = cashes;
    }
}
