package vendingmachine.controller;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Card;

import java.io.IOException;

public class CardPaymentController {

    private VendingMachineModel model = new VendingMachineModel();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cardName;


    public CardPaymentController() throws IOException, ParseException {
    }

    public void cardInfo() {
        String name = cardName.getText();
        String number = cardNumber.getText();
    }
}
