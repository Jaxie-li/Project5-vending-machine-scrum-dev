package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vendingmachine.utils.Cash;

import java.io.IOException;

public class CashModifierController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField fiftyCentsAmount;

    @FXML
    private TextField fiftyDollarsAmount;

    @FXML
    private TextField fiveCentsAmount;

    @FXML
    private TextField fiveDollarsAmount;

    @FXML
    private TextField hundredDollarsAmount;

    @FXML
    private TextField oneDollarAmount;

    @FXML
    private TextField tenCentsAmount;

    @FXML
    private TextField tenDollarsAmount;

    @FXML
    private TextField twentyCentsAmount;

    @FXML
    private TextField twentyDollarsAmount;

    @FXML
    private TextField twoDollarsAmount;

    @FXML
    private Button updateMoney;

    private AppController appController;

    public void cashierMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Cashier.fxml"));
        root = loader.load();
        loader.setController(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateCash(ActionEvent event) throws IOException {
        int[] value = { 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5 };
        TextField[] tfs = new TextField[]{
                hundredDollarsAmount, fiftyDollarsAmount, twentyDollarsAmount, tenDollarsAmount, fiveDollarsAmount,
                twoDollarsAmount, oneDollarAmount, fiftyCentsAmount, twentyCentsAmount, tenCentsAmount, fiveCentsAmount
        };
        try {
            int[] newAmounts = new int[11];
            for (int i = 0; i < 11; i++) {
                String str = tfs[i].getText().strip();

                if (str.equals("")) {
                    newAmounts[i] = -1;
                    continue;
                }

                int temp = Integer.parseInt(str);
                if (temp < 0) {
                    throw new NumberFormatException();
                }
                newAmounts[i] = temp;
            }

            for (int i = 0; i < 11; i++) {
                if (newAmounts[i] != -1) {
                    for (Cash cash : appController.getModel().getCashes()) {
                        if (cash.getValue() == value[i]) {
                            cash.setQuantity(newAmounts[i]);
                            cash.updateQuantity();
                        }
                    }
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Update Success!");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Positive Integer Only!");
            alert.showAndWait();
        }


    }

    public void init(AppController ac) {
        this.appController = ac;
    }
}