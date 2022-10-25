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
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.PasswordFieldSkin;
import vendingmachine.utils.User;

import java.io.IOException;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:53 am
 * @description: This is the controller for the App class, and it will take the responsibility of GUI Action & Response
 */
public class AppController{
    private VendingMachineModel model = new VendingMachineModel();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

//    @FXML
//    private PasswordFieldSkin skin;

    @FXML
    private AnchorPane userComponent;



//    public void setPasswordSkin() {
//        password.setSkin(new PasswordFieldSkin(password));
//    }

    public AppController() throws IOException, ParseException {
    }



    public void listProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/ListProduct.fxml"));
        root = loader.load();
        ListProductController listProductController = loader.getController();
        listProductController.init(this);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signInCheck(ActionEvent event) throws IOException {
//        password.setSkin(new PasswordFieldSkin(password));
        User user = User.isValidUser(username.getText(), password.getText());


        if (user != null) {
            this.model.setCurrentUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Log In Success!");
            alert.setContentText(String.format("Welcome %s",user.getUsername()));
            alert.showAndWait();
//            userComponent.setVisible(false);

            if(user.getUserType().equals("seller")){
                FXMLLoader loader =new FXMLLoader(getClass().getResource("/vendingmachine/GUI/SellerPage.fxml"));
                root = loader.load();
                SellerPageController sellerPageController = loader.getController();
                sellerPageController.init(this);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            username.setText("");
            password.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Log In Failed!");
            alert.setContentText("Incorrect Username or password");
            alert.showAndWait();
        }


    }

    public void register(){
        User newUser = User.register(username.getText(), password.getText());
        this.model.setCurrentUser(newUser);
        userComponent.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setContentText("Registration Successful!");
        alert.showAndWait();
    }

    public VendingMachineModel getModel() {
        return model;
    }
}
