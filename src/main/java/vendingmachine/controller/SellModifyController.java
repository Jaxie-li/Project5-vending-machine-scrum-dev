package vendingmachine.controller;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import vendingmachine.utils.DBModel;
import vendingmachine.utils.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Ref;
import java.util.ArrayList;


public class SellModifyController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static final String[] CATEGORY = new String[]{"drinks","chocolates","chips","candies"};
    private AppController appController;
    @FXML
    private ChoiceBox<Product> itemNameBox;
    @FXML
    private Label nameLabelOld;
    @FXML
    private Label priceLabelOld;
    @FXML
    private Label categoryLabelOld;
    @FXML
    private Label quantityLabelOld;

    @FXML
    private TextField newCode;
    @FXML
    private TextField newName;
    @FXML
    private TextField newPrice;
    @FXML
    private ChoiceBox<String> newCategory;
    @FXML
    private TextField newQuantity;


    public void init(AppController appController){
        this.appController = appController;
        itemNameBox.getItems().addAll(appController.getModel().getProducts());
        newCategory.getItems().addAll(CATEGORY);

        itemNameBox.setOnAction((event) -> {
            Object selectedItem = itemNameBox.getSelectionModel().getSelectedItem();

            Product newProduct = (Product) selectedItem;

            nameLabelOld.setText(newProduct.getItemName());
            priceLabelOld.setText(String.format("$%.2f",newProduct.getItemPrice()/100.0));
            categoryLabelOld.setText(newProduct.getItemCategory());
            quantityLabelOld.setText(String.valueOf(newProduct.getItemQuantity()));
        });
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/SellerPage.fxml"));
        root = loader.load();
        SellerPageController sellerPageController = loader.getController();
        sellerPageController.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modify(ActionEvent event) throws IOException {
        /*
        Type Check
         */
        try{
            int code = Integer.parseInt(newCode.getText());
            String name = newName.getText();
            int price = (int)Double.parseDouble(newPrice.getText())*100;
            String category = newCategory.getSelectionModel().getSelectedItem();
            int quantity = Integer.parseInt(newQuantity.getText());

            Product newProduct = new Product(code,name,price,category,quantity);

            appController.getModel().getProducts().remove(itemNameBox.getSelectionModel().getSelectedItem());
            appController.getModel().getProducts().add(newProduct);


            ArrayList<Product> products = appController.getModel().getProducts();
            products.sort((o1,o2)-> o1.getItemCode()-o2.getItemCode()>0?1:-1);


            FileWriter writer = new FileWriter("src/main/resources/vendingmachine/data/product.json");
            writer.write(new Gson().toJson(products));
            writer.close();

            System.out.println("Successfully!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modify Successfully");
            alert.setContentText("Done");
            alert.showAndWait();
            back(event);
        }catch (Exception e) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Modify Failed!");
           alert.setContentText("Please check your input.");
           alert.showAndWait();
        }

    }




}
