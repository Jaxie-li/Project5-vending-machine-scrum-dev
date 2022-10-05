package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.User;

import java.io.IOException;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:53 am
 * @description: This is the controller for the App class, and it will take the responsibility of GUI Action & Response
 */
public class AppController{
    private VendingMachineModel model = new VendingMachineModel("src/main/resources/vendingmachine/vending_machine_initial_state.json");

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord;

    @FXML
    private AnchorPane userComponent;




    public AppController() throws IOException, ParseException {

    }



    public void listProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/ListProduct.fxml"));
        root = loader.load();
        ListProductController listProductController = loader.getController();
        listProductController.init(this);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signInCheck(){
        User user = User.isValidUser(userName.getText(),passWord.getText());

        if(user != null){
            this.model.setCurrentUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Log In Success!");
            alert.setContentText(String.format("Welcome %s",user.getUserName()));
            alert.showAndWait();
            userComponent.setVisible(false);
        }else{
            userName.setText("");
            passWord.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Log In Failed!");
            alert.setContentText("Incorrect Username or password");
            alert.showAndWait();
        }


    }

    public VendingMachineModel getModel() {
        return model;
    }
}
