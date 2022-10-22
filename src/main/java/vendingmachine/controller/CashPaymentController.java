package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import vendingmachine.utils.DBModel;
import vendingmachine.utils.Order;

import java.io.IOException;
import java.util.Optional;

public class CashPaymentController {
    private AppController appController;

    private Order model;
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
    private TextField inputAmount;

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
    private Label errorFlag;

    public double balance;

    public void init(AppController appController) {
        this.appController = appController;
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/CheckOrder.fxml"));
        root = loader.load();
        GenerateOrderController generateOrderControl = loader.getController();
        generateOrderControl.setModel(model);
        generateOrderControl.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is for pay button. This will decide whether the balance is enough or not
     * @param event click the button
     * @throws IOException throws exceptions
     */
    @FXML
    private void pay(ActionEvent event) throws IOException {

        if (balance > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Not enough balance :( ");
            alert.setContentText("Do you want to cancel transaction?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isEmpty()) {
                System.out.println("Error!");
            } else if (result.get() == ButtonType.OK) {

                System.out.println("you Successfully cancel the transaction.");

                // go to the main page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else if (result.get() == ButtonType.CANCEL) {
                errorFlag.setText("Insert enough balance!");
                System.out.println("Insert enough balance!");
            }

        } else {
            // check out successfully

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Welcome to use ! ");

            // TBD
            String checkOutInfo = "You will get your products " + " " + " and changes " + " ";
            alert.setContentText(checkOutInfo);

            Optional<ButtonType> button = alert.showAndWait();

            if (button.isEmpty()) {
                System.out.println("Error");
            } else if (button.get() == ButtonType.OK) {
                System.out.println("Click OK");
                // go back to previous page and log out
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /**
     * Insert each note, will sum the balance
     * @param event click sum button to sum the balance
     */
    @FXML
    public void sum(ActionEvent event) throws IOException {
        try {
            int hundredDollar = Integer.parseInt(hundredDollarsAmount.getText());
            int fiftyDollar = Integer.parseInt(fiftyDollarsAmount.getText());
            int twentyDollar = Integer.parseInt(twentyDollarsAmount.getText());
            int tenDollar = Integer.parseInt(tenDollarsAmount.getText());
            int fiveDollar = Integer.parseInt(fiveDollarsAmount.getText());
            int twoDollar = Integer.parseInt(twoDollarsAmount.getText());
            int oneDollar = Integer.parseInt(oneDollarAmount.getText());

            int fiftyCent = Integer.parseInt(fiftyCentsAmount.getText());
            int twentyCent = Integer.parseInt(twentyCentsAmount.getText());
            int tenCent = Integer.parseInt(tenCentsAmount.getText());
            int fiveCent = Integer.parseInt(fiveCentsAmount.getText());

            Double amount = 100.00 * hundredDollar + 50.00 * fiftyDollar + 20.00 * twentyDollar + 10.00 * tenDollar
                    + 5.00 * fiveDollar + 2.00 * twoDollar + 1.00 * oneDollar + 0.50 * fiftyCent + 0.20 * twentyCent
                    + 0.10 * tenCent + 0.05 * fiveCent;

            this.balance = amount;
            inputAmount.setText(String.format("%.2f", amount));

//            DBModel db = new DBModel() {
//                @Override
//                public JSONObject serialise() {
//                    return null;
//                }
//            };

        } catch (NumberFormatException nfe) {

            System.out.println("Input error");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Insert Error :( ");
            alert.setContentText("Check input :<");

            alert.showAndWait();}
        }
    public void setModel(Order model) {
        this.model = model;
    }
}

