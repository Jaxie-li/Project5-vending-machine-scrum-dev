package vendingmachine.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Card;

import java.io.IOException;

public class CashPaymentController {

    private VendingMachineModel model = new VendingMachineModel();

    private Stage stage;
    private Scene scene;
    private Parent root;


    public CashPaymentController() throws IOException, ParseException {
    }


    public void cashCheck() {
    }


}
