package vendingmachine.controller;

import exceptions.UserNameExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Order;
import vendingmachine.utils.User;

import java.io.IOException;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:53 am
 * @description: This is the controller for the App class, and it will take the responsibility of GUI Action & Response
 */
public class AppController {
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
    private AnchorPane userComponent;
    @FXML
    private Button back;
    @FXML
    private Button login;
    @FXML
    private Button logout;
    @FXML
    private Button change_seller;
    @FXML
    private Button change_cashier;
    @FXML
    private Button money;
    @FXML
    private Button manage_add_delete;
    @FXML
    private Text lastFiveOrderText;

    @FXML
    private Text anonymousUserOrderText;


    public AppController() throws IOException, ParseException {
    }

    public void init(){
        User anonymousUser = new User("","","customer");
        Order anonymousOrder = new Order(anonymousUser);
        anonymousUserOrderText.setText(anonymousOrder.getLastFiveOrder(anonymousUser));
        anonymousUserOrderText.setVisible(true);
        lastFiveOrderText.setVisible(false);
    }


    public void listProduct(ActionEvent event) throws IOException {
        lastFiveOrderText.setVisible(false);
        anonymousUserOrderText.setVisible(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/ListProduct.fxml"));
        root = loader.load();
        ListProductController listProductController = loader.getController();
        listProductController.init(this);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    public void signInCheck(ActionEvent event) throws IOException {
        User user = User.isValidUser(username.getText(), password.getText());

        if (user != null) {
            // set current user
            this.model.setCurrentUser(user);
            Order currentUserOrder = new Order(user);


            lastFiveOrderText.setVisible(false);
            anonymousUserOrderText.setVisible(true);


            // change to different pages according to different user types

            if (user.getUserType().equals("seller")) {
                FXMLLoader loader =new FXMLLoader(getClass().getResource("/vendingmachine/GUI/SellerPage.fxml"));
                root = loader.load();
                SellerPageController sellerPageController = loader.getController();
                sellerPageController.init(this);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (user.getUserType().equals("cashier")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Cashier.fxml"));
                root = loader.load();
                CashierController cashierController = loader.getController();
                cashierController.init(this);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (user.getUserType().equals("owner")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Owner.fxml"));
                root = loader.load();
                OwnerController ownerController = loader.getController();
                ownerController.init(this);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (user.getUserType().equals("admin")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Admin.fxml"));
                root = loader.load();
                AdminController adminController = loader.getController();
                adminController.init(this);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (user.getUserType().equals("customer")) {
                System.out.println("cus");
                userComponent.setVisible(false);
                logout.setVisible(true);
                lastFiveOrderText.setText(currentUserOrder.getLastFiveOrder(user));
                lastFiveOrderText.setVisible(true);
                anonymousUserOrderText.setVisible(false);
            }
            // alert the user their login was successful
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Log In Success!");
            alert.setContentText(String.format("Welcome %s",user.getUsername()));
            alert.showAndWait();
        }
        // otherwise the login check failed, prompt the user to enter again
        else {
            username.setText("");
            password.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Log In Failed!");
            alert.setContentText("Incorrect Username or password");
            alert.showAndWait();
        }
    }

    public void register() throws ParseException, IOException {
        try {
            User newUser = User.register(username.getText(), password.getText());

            model = new VendingMachineModel();
            this.model.setCurrentUser(newUser);
            userComponent.setVisible(false);
            logout.setVisible(true);
            Order currentUserOrder = new Order(newUser);
            anonymousUserOrderText.setVisible(false);
            lastFiveOrderText.setText(currentUserOrder.getLastFiveOrder(newUser));
            lastFiveOrderText.setVisible(true);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Register");
            alert.setContentText("Registration Successful!");
            alert.showAndWait();
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

    public VendingMachineModel getModel() {
        return model;
    }

    //logout and re-login
    public void Logout(ActionEvent event) throws IOException {
        username.setText("");
        password.setText("");
        this.model.setCurrentUser(null);
        userComponent.setVisible(true);
        logout.setVisible(false);
        lastFiveOrderText.setVisible(false);
        anonymousUserOrderText.setVisible(true);

    }

//    public void listLastFivePurchase(ActionEvent actionEvent) {
//        User currentUser = model.getCurrentUser();
//        Order currentUserOrder = new Order(currentUser);
//        lastFiveOrderText.setText(currentUserOrder.getLastFiveOrder(currentUser));
//    }

    public void test(){
        System.out.println(String.format("anoy: %s\t data:%s",anonymousUserOrderText.isVisible(),anonymousUserOrderText.getText()));
        System.out.println(String.format("anoy: %s\t data:%s",lastFiveOrderText.isVisible(),lastFiveOrderText.getText()));
    }

}
