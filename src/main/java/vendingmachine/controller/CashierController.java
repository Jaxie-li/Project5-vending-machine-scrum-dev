package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private Button back;
    @FXML private Button change;

    public void cashierMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/cashier.fxml"));
        Parent root = loader.load();
        stage = (Stage) (back.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
