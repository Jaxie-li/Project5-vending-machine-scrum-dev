package vendingmachine.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import vendingmachine.utils.Cash;
import vendingmachine.utils.Product;
import vendingmachine.utils.User;

import java.io.FileNotFoundException;
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
    private ArrayList<Product> products;
    private ArrayList<Cash> cashes;


    public VendingMachineModel(String config) throws IOException, ParseException {
        JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(config));
        JSONArray productsJSONArray = (JSONArray) data.get("product");
        JSONArray cashesJSONArray = (JSONArray) data.get("cash");

        //Save the Product instance into products

        //Save the Cash instance into cashes

    }
}
