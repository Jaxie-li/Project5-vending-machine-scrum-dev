package vendingmachine.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vendingmachine.App;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Card;
import vendingmachine.utils.Order;
import vendingmachine.utils.User;

import java.io.IOException;
import java.util.Optional;

public class CardPaymentController {

    private AppController appController;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cardName;
    private Order model;

    public void init(AppController appController){
        this.appController = appController;
    }


    public CardPaymentController() throws IOException, ParseException {
    }

    public String getCardName() {
        return cardName.getText();
    }

    public String getCardNumber() {
        return cardNumber.getText();
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

    public void setModel(Order model) {
        this.model = model;
    }

    public void pay(ActionEvent actionEvent) {
        // get the current user
        User currentUser = appController.getModel().getCurrentUser();

        // if the current user is logged in (i.e., not anonymous)
        if (currentUser != null) {
            // check if the card is valid
            boolean valid = Card.checkCreditCardValid(getCardName(), getCardNumber(), currentUser);

            // TODO: valid VS saved card
            if (valid) {
                // ask if they want to save the card information
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Save credit card information");
                alert.setContentText("Would you like to save the card information for future use?");
                Optional<ButtonType> action = alert.showAndWait();

                // if they said ok, then save the card info
                if (action.isPresent() && action.get() == ButtonType.OK) {
                    appController.getModel().getCurrentUser().setSavedCard(
                            new Card(cardName.getText(),cardNumber.getText()));
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid card");
                alert.setContentText("Payment declined: Please check your card details and try again.");
                alert.showAndWait();
            }
        } else {
            // check if the card is valid
            boolean valid = Card.checkCreditCardValid(getCardName(), getCardNumber());
        }
    }
}
