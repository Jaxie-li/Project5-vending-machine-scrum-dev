package vendingmachine.controller;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import vendingmachine.App;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import static vendingmachine.utils.DBModel.read;

public class CardPaymentController {

    private AppController appController;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PasswordField cardNumber;

    @FXML
    private TextField cardName;

    @FXML
    private CheckBox isSave;
    private Order model;

    public void init(AppController appController){

        this.appController = appController;
        User user = appController.getModel().getCurrentUser();
        if(user!=null){
            cardNumber.setText(user.getSavedCard().getNumber()!=null?user.getSavedCard().getNumber():"");
            cardName.setText(user.getSavedCard().getName()!=null?user.getSavedCard().getName():"");
        }else{
            isSave.setVisible(false);
        }
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
        cancelOrder("userCancelled");
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

    public void pay(ActionEvent event) throws IOException {
        // get the current user
        User currentUser = appController.getModel().getCurrentUser();
        Boolean isValid = false;


        // if the current user is logged in (i.e., not anonymous)
        if (currentUser != null) {
            isValid = Card.checkCreditCardValid(getCardName(), getCardNumber(), currentUser,isSave.isSelected());
            if(isSave.isSelected()){
                User.update(read(User.path),currentUser.serialise(),User.path,"username");
            }
        } else {
            isValid = Card.checkCreditCardValid(getCardName(), getCardNumber());
        }


        if (isValid) {
            updateProductStock();
            model.addOrder();



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully");
            alert.setContentText(receivedProducts());
            alert.showAndWait();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
            root = loader.load();
            loader.setController(appController);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid card");
            alert.setContentText("Payment declined: Please check your card details and try again.");
            alert.showAndWait();
        }
    }

    public void updateProductStock() {

        for (Product soldProduct : model.getProducts()) {
            int sellQuantity = soldProduct.getItemQuantity();

            for (Product machineProduct : appController.getModel().getProducts()) {
                if (machineProduct.getItemName().equals(soldProduct.getItemName())) {
                    machineProduct.setItemQuantity(machineProduct.getItemQuantity() - sellQuantity);
                    machineProduct.updateStock();
                }
            }
        }
    }

    public void cancelOrder(String reason) {
        CancelledOrder cancelledOrder = new CancelledOrder();
        cancelledOrder.setId(cancelledOrder.getNextId());

        if (model.getUsername().equals("")) {
            cancelledOrder.setUsername("anonymous");
        } else {
            cancelledOrder.setUsername(model.getUsername());
        }

        if (reason.equals("userCancelled")) {
            cancelledOrder.setReason("user cancelled");

        }

        cancelledOrder.addCancelOrder();
    }

    public String receivedProducts() {
        StringBuilder outputProducts = new StringBuilder();
        for (Product p : model.getProducts()) {
            outputProducts.append(p.getItemName());
            outputProducts.append(": ");
            outputProducts.append(p.getItemQuantity());
            outputProducts.append(" \n");
        }
        return outputProducts.toString();
    }
}
