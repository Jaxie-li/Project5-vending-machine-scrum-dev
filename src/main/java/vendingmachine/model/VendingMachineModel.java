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
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Cash> cashes = new ArrayList<Cash>();


    public VendingMachineModel(String config) throws IOException, ParseException {
        // read the config file, split the config file into products and cashes sections
        JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(config));
        JSONArray productsJSONArray = (JSONArray) data.get("product");
        JSONArray cashesJSONArray = (JSONArray) data.get("cash");

        // save the Product instance into products
        for (Object o : productsJSONArray) {
            JSONObject each = (JSONObject) o;
            this.products.add(new Product(
                    Integer.valueOf(each.get("item_code").toString()),
                    (String) each.get("item_name"),
                    (double) each.get("item_price"),
                    (String) each.get("item_category"),
                    Integer.valueOf(each.get("item_quantity").toString())
            ));
        }
//        // save the Cash instance into cashes
//        for (Object o : cashesJSONArray) {
//            JSONObject each = (JSONObject) o;
//            this.cashes.add(new Cash(
//                    (double) each.get("value"),
//                    (int) each.get("quantity")
//            ));
//        }
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
