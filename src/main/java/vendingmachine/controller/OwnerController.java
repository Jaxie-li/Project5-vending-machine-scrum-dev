package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vendingmachine.utils.*;

import java.io.IOException;
import java.util.Optional;

import static vendingmachine.utils.DBModel.read;

public class OwnerController {
    @FXML
    private Button back;
    @FXML
    private Button add;
    @FXML
    private Button delete;

    @FXML
    private Button summaryCancelledTransactions;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private AppController appController;

    public void init(AppController appController) {
        this.appController = appController;
    }

    //sub function want to go back owner main page.
    public void ownerMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Owner.fxml"));
        root = loader.load();
        loader.setController(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
        root = loader.load();
        loader.setController(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSeller(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/SellerPage.fxml"));
        root = loader.load();
        SellerPageController sellerPageController = loader.getController();
        sellerPageController.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeCashier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Cashier.fxml"));
        root = loader.load();
        CashierController cashierController = loader.getController();
        cashierController.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void changeManagement(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/OwnerAddDelete.fxml"));
//        root = loader.load();
//        loader.setController(appController);
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    public void changeManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/OwnerAddDelete.fxml"));
        root = loader.load();
        OwnerController ownerController = loader.getController();
        ownerController.init(appController);
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
    void summaryCancelledTransactions(ActionEvent event) throws IOException {
        Json2Txt json2Txt = new Json2Txt();
        json2Txt.generateTXT("src/main/resources/vendingmachine/summary/cancelled_transactions.txt",
                CancelledOrder.getData());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Successfully ! ");
        alert.setContentText("Generate summary of cancelled transactions successfully !");
        Optional<ButtonType> button = alert.showAndWait();

        if (button.isEmpty()) {
            System.out.println("Click close pop up window");
        } else if (button.get() == ButtonType.OK) {
            System.out.println("Click OK button");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Owner.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void generateUserInformation(ActionEvent actionEvent) throws IOException {
        new UserReport(appController.getModel().getUsers()).write();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Generate Success!");
        alert.showAndWait();
    }
}

