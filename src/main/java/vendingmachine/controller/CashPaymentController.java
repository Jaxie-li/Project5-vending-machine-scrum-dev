package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Optional;

public class CashPaymentController {
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
                System.out.println("Error: pop up button!");
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
        }
    }

    /**
     * Insert each note, will sum the balance
     * @param event click sum button to sum the balance
     */
    @FXML
    public void sum(ActionEvent event) {
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
    }

}
