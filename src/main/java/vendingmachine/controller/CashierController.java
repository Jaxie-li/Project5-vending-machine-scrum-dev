package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vendingmachine.utils.CashReport;
import vendingmachine.utils.TransactionReport;

import java.io.IOException;

public class CashierController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button transBtn;

    private AppController appController;

    public void init(AppController appController) {
        this.appController = appController;
    }

    //sub function want to go back to main cashier page.
    public void cashierMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Cashier.fxml"));
        root = loader.load();
        loader.setController(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void generateTransaction(ActionEvent event) throws IOException {
        new TransactionReport(appController.getModel().getOrders()).write();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Generate Success!");
        alert.showAndWait();
    }

    public void generateCash(ActionEvent event) throws IOException {
        new CashReport(appController.getModel().getCashes()).write();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Generate Success!");
        alert.showAndWait();

    }

    public void returnToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeMoney(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/CashierMoney.fxml"));
        root = loader.load();
        CashModifierController cmc = loader.getController();
        cmc.init(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void listProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/ListProduct.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
