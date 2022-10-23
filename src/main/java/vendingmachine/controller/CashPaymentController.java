package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Cash;
import vendingmachine.utils.Order;
import vendingmachine.utils.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class CashPaymentController {
    private AppController appController;

    private Order model;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField fiftyCentsAmount;

    @FXML
    private TextField fiftyDollarsAmount;

    @FXML
    private TextField fiveCentsAmount;

    @FXML
    private TextField fiveDollarsAmount;

    @FXML
    private TextField hundredDollarsAmount;

    @FXML
    private TextField inputAmount;

    @FXML
    private TextField oneDollarAmount;

    @FXML
    private TextField tenCentsAmount;

    @FXML
    private TextField tenDollarsAmount;

    @FXML
    private TextField twentyCentsAmount;

    @FXML
    private TextField twentyDollarsAmount;

    @FXML
    private TextField twoDollarsAmount;

    @FXML
    private Label errorFlag;

    @FXML
    private Text orderPrice;

    @FXML
    private Button payButton;

    private double balance;

    private ArrayList<Cash> exchange;

    public void init(AppController appController) {
        this.appController = appController;
    }

    /**
     * Get the order from previous page >> get the order's detail, products and price
     *
     * @param model user's order
     */
    public void setModel(Order model) {
        this.model = model;
        orderPrice.setText("" + this.model.getOrderTotal());
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/CheckOrder.fxml"));
        root = loader.load();
        GenerateOrderController generateOrderControl = loader.getController();
        generateOrderControl.setModel(model);
        generateOrderControl.init(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is for pay button. This will decide whether the balance is enough or not
     *
     * @param event click the button
     * @throws IOException throws exceptions
     */
    @FXML
    private void pay(ActionEvent event) throws IOException, ParseException {
        if (balance <= 0) {
            invalidBalance();

        } else if (balance < model.getOrderTotal()) {
            checkOutUnsuccessful(event);

        } else {
            // can not find sufficient changes
//            System.out.println(model.getOrderTotal());
//            System.out.println(balance);
            // TBD
            exchange = Cash.payCash(model.getOrderTotal(), balance, new VendingMachineModel().getCashes());
//            System.out.println(new VendingMachineModel().getCashes());
            if (exchange == null) {
                System.out.println("Insufficient Changes");
                findChangesUnsuccessful(event);

            } else {
                // find sufficient changes >> check out successfully, user receives products and changes
                checkOutSuccess(event);

                // update the stock
                updateProductStock();
                updateCashStock();
            }
        }
    }

    /**
     * Insert each note, will sum the balance
     *
     * @param event click sum button to sum the balance
     */
    @FXML
    public void sum(ActionEvent event) {
        try {
            int hundredDollar = Integer.parseInt(hundredDollarsAmount.getText());
            int fiftyDollar = Integer.parseInt(fiftyDollarsAmount.getText());
            int twentyDollar = Integer.parseInt(twentyDollarsAmount.getText());
            int tenDollar = Integer.parseInt(tenDollarsAmount.getText());
            int fiveDollar = Integer.parseInt(fiveDollarsAmount.getText());
            int twoDollar = Integer.parseInt(twoDollarsAmount.getText());
            int oneDollar = Integer.parseInt(oneDollarAmount.getText());

            int fiftyCent = Integer.parseInt(fiftyCentsAmount.getText());
            int twentyCent = Integer.parseInt(twentyCentsAmount.getText());
            int tenCent = Integer.parseInt(tenCentsAmount.getText());
            int fiveCent = Integer.parseInt(fiveCentsAmount.getText());

            Double amount = 100.00 * hundredDollar + 50.00 * fiftyDollar + 20.00 * twentyDollar + 10.00 * tenDollar
                    + 5.00 * fiveDollar + 2.00 * twoDollar + 1.00 * oneDollar + 0.50 * fiftyCent + 0.20 * twentyCent
                    + 0.10 * tenCent + 0.05 * fiveCent;

            this.balance = amount;
            inputAmount.setText(String.format("%.2f", amount));

//            DBModel db = new DBModel() {
//                @Override
//                public JSONObject serialise() {
//                    return null;
//                }
//            };

        } catch (NumberFormatException nfe) {

            System.out.println("Input error");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Insert Error :( ");
            alert.setContentText("Check input :<");

            alert.showAndWait();
        }
    }

    /**
     * Check out successfully, user will get the products and changes
     *
     * @param event click pay button
     * @throws IOException throws any exception
     */
    public void checkOutSuccess(ActionEvent event) throws IOException {
        // check out successfully
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Welcome to use ! ");

        String checkOutInfo = "You will get:\n"
                + "Products:\n" + receivedProducts() + "\n"
                + "changes:\n" + receiveChanges();
        alert.setContentText(checkOutInfo);

        Optional<ButtonType> button = alert.showAndWait();

        if (button.isEmpty()) {
            System.out.println("Error");

        } else if (button.get() == ButtonType.OK) {
            System.out.println("Click OK");
            // go back to main page and log out
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * check out unsuccessfully, user will not get the products and changes
     * >> case : 1. not enough money
     * >> two options: 1. cancel the transaction 2. reinsert the coins or notes
     *
     * @param event click pay button
     * @throws IOException throws any exception
     */
    public void checkOutUnsuccessful(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Not enough balance :( ");
        alert.setContentText("Do you want to cancel transaction?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty()) {
            System.out.println("Error!");
        } else if (result.get() == ButtonType.OK) {

            System.out.println("You have successfully cancel the transaction.");
            // go to the main page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else if (result.get() == ButtonType.CANCEL) {
            errorFlag.setText("Insert enough balance!");
            System.out.println("Insert enough balance!");
        }
    }

    /**
     * Received money is larger than order price, but machine can't find sufficient changes
     *
     * @param event click pay button
     * @throws IOException throws any exception
     */
    public void findChangesUnsuccessful(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Not enough changes :( ");
        alert.setContentText("Do you want to change notes or coins?   Click CANCEL to cancel the transaction");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()) {
            System.out.println("Error!");

        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("You have successfully cancel the transaction.");
            // go to the main page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else if (result.get() == ButtonType.OK) {
            errorFlag.setText("Insert different notes or coins!");
            System.out.println("Insert different notes or coins!");
        }
    }

    /**
     * There is invalid notes or coins, pop up a window to remind to reinsert
     */
    public void invalidBalance() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Invalid Balance :( ");
        alert.setContentText("Check your balance");

        alert.showAndWait();
    }

    /**
     * A string to show received products
     *
     * @return string builder for received products
     */
    public StringBuilder receivedProducts() {
        StringBuilder outputProducts = new StringBuilder();
        for (Product p : model.getProducts()) {
            outputProducts.append(p.getItemName());
            outputProducts.append(": ");
            outputProducts.append(p.getItemQuantity());
            outputProducts.append(" \n");
        }
        return outputProducts;
    }

    /**
     * A string to show received changes
     *
     * @return string builder for received products
     */
    public StringBuilder receiveChanges() {
        StringBuilder outputChangess = new StringBuilder();
        for (Cash c : exchange) {
            outputChangess.append("$");
            outputChangess.append(c.getValue());
            outputChangess.append(": ");
            outputChangess.append(c.getQuantity());
            outputChangess.append(" \n");

        }
        return outputChangess;
    }

    /**
     * Update the products stock in the database after users receive products
     *
     * @throws IOException    throws IO error
     * @throws ParseException throws Parse error
     */
    public void updateProductStock() throws IOException, ParseException {

        for (Product soldProduct : model.getProducts()) {
            int sellQuantity = soldProduct.getItemQuantity();
            VendingMachineModel vendingMachineModel = new VendingMachineModel();

            for (Product machineProduct : vendingMachineModel.getProducts()) {
                if (machineProduct.getItemName().equals(soldProduct.getItemName())) {
                    machineProduct.setItemQuantity(machineProduct.getItemQuantity() - sellQuantity);

                    // TBD >> Write Back to Product.json
                    System.out.println(machineProduct.getItemName());
                    System.out.println(machineProduct.getItemQuantity());
                }
            }
        }
    }

    /**
     * Update the cashes stock in the database after users receive changes
     *
     * @throws IOException    throws IO error
     * @throws ParseException throws Parse error
     */
    public void updateCashStock() throws IOException, ParseException {

        for (Cash cashes : exchange) {
            VendingMachineModel vendingMachineModel = new VendingMachineModel();
            for (Cash machineCash : vendingMachineModel.getCashes()) {
                if (machineCash.getValue() == cashes.getValue()) {
                    machineCash.setQuantity(machineCash.getQuantity() + cashes.getQuantity());

                    // TBD >> Write back to Cash.json
                    System.out.println(machineCash.getValue());
                    System.out.println(machineCash.getQuantity());
                }
            }
        }
    }

}

