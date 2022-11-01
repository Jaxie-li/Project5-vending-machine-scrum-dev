package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vendingmachine.utils.Json2Txt;
import vendingmachine.utils.Product;

import java.io.IOException;
import java.util.Optional;

import static vendingmachine.utils.DBModel.read;

public class SellerPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private AppController appController;

    public void init(AppController appController){
        this.appController = appController;
    }

    public void returnToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void sellerModify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/SellerModify.fxml"));
        root = loader.load();
        SellModifyController sellModifyController= loader.getController();
        sellModifyController.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void listProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/ListProduct.fxml"));
        root = loader.load();
        ListProductController listProductController = loader.getController();
        listProductController.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void summaryAvailableProducts(ActionEvent event) throws IOException {
        Json2Txt json2Txt = new Json2Txt();

        JSONArray availableProducts = new JSONArray();
        for (Object productInDatabase : read(Product.path)) {
            Product product = new Product((JSONObject) productInDatabase);
            if (product.getItemQuantity() > 0) {
                availableProducts.add(productInDatabase);
            }
        }

        json2Txt.generateTXT("src/main/resources/vendingmachine/summary/available_products.txt",
                availableProducts);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Successful");
        alert.setContentText("Generate summary of available products successfully!");

        Optional<ButtonType> button = alert.showAndWait();
        if (button.isEmpty()) {
            System.out.println("Click close");
        } else if (button.get() == ButtonType.OK) {
            System.out.println("Click OK button");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/SellerPage.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void modifyProductInfo(ActionEvent event) throws IOException {
        // change to seller modify page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/SellerModify.fxml"));
        root = loader.load();
        SellModifyController sellModifyController = loader.getController();
        sellModifyController.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
