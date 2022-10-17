package vendingmachine.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vendingmachine.App;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Card;

import java.io.IOException;

public class CardPaymentController {

    private AppController appController;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cardName;

    public void init(AppController appController){
        this.appController = appController;
    }


    public CardPaymentController() throws IOException, ParseException {
    }

    public void cardInfo() {
        String name = cardName.getText();
        String number = cardNumber.getText();
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/CheckOrder.fxml"));
        root = loader.load();
        GenerateOrderController generateOrderControl = loader.getController();
        generateOrderControl.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
