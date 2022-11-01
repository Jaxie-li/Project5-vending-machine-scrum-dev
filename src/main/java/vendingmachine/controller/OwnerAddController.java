package vendingmachine.controller;

import exceptions.UserNameExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Order;
import vendingmachine.utils.User;

import java.io.IOException;

public class OwnerAddController {

    private String[] userTypes = new String[]{"admin","customer","seller","cashier"};
    @FXML
    private Button add;
    @FXML
    private Button back;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private AppController appController;

    @FXML
    private ChoiceBox<String> userType;

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    public void init(AppController appController) {
        this.appController = appController;
        userType.getItems().addAll(userTypes);
    }

    //sub function want to go back owner main page.
    public void ownerMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Owner.fxml"));
        root = loader.load();
        OwnerController oc = loader.getController();
        oc.init(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void register(ActionEvent even) throws ParseException, IOException {

        try {
            appController.getModel().getUsers().add(User.register(username.getText(), password.getText(),userType.getSelectionModel().getSelectedItem()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Register");
            alert.setContentText("Registration Successful!");
            alert.showAndWait();
            ownerMain(even);
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register");
            alert.setContentText("Name Already Exists!");
            alert.showAndWait();
        } catch (UserNameExistException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register");
            alert.setContentText("Name Already Exists!");
            alert.showAndWait();
        }

    }
}
