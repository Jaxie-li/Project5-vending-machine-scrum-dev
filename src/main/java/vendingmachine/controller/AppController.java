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
    private String userType;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordFieldSkin skin;

    @FXML
    private AnchorPane userComponent;
    @FXML
    private Button logout;
    @FXML
    private TextField uname;
    @FXML
    private Button back;
    @FXML
    private PasswordField pw;
    @FXML
    private Button login;
    @FXML
    private Button change_seller;
    @FXML
    private Button change_cashier;
    @FXML private Button money;
    @FXML private Button manage_add_delete;


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
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void signInCheck(ActionEvent actionEvent) throws IOException{
        pw.setSkin(new PasswordFieldSkin(pw));
        User user = User.isValidUser(uname.getText(), pw.getText());

        if (user != null) {
            //check the username and password,
            this.model.setCurrentUser(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Log In Success!");
            alert.setContentText(String.format("Welcome %s",user.getUsername()));
            alert.showAndWait();
            userComponent.setVisible(false);
            //check customer
            //if (this.user_type.equals("customer")){

            //check seller
            if (getUserType().equals("seller")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/Seller.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) (login.getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            //check cashier
            else if (getUserType().equals("cashier")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/Cashier.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) (login.getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            //check owner
            else if (getUserType().equals("owner")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/Owner.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) (login.getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }


        else if (uname.getText().isEmpty() || pw.getText().isEmpty()){
            uname.setText("");
            pw.setText("");
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

    //logout and re-login
    public void Logout(ActionEvent actionEvent) throws IOException {
        this.model.setCurrentUser(null);
        userComponent.setVisible(true);
    }
    public void returnToMainPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/App.fxml"));
        Parent root = loader.load();
        stage = (Stage) (back.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void changeSeller(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/seller.fxml"));
        Parent root = loader.load();
        stage = (Stage) (change_seller.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void changeCashier(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/cashier.fxml"));
        Parent root = loader.load();
        stage = (Stage) (change_cashier.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void changeMoney(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/CashierMoney.fxml"));
        Parent root = loader.load();
        stage = (Stage) (money.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void changeManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/OwnerAddDelete.fxml"));
        Parent root = loader.load();
        stage = (Stage) (manage_add_delete.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
