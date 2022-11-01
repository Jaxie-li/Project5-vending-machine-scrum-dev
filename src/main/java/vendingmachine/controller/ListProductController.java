package vendingmachine.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vendingmachine.components.ProductComponents;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.CancelledOrder;
import vendingmachine.utils.Order;
import vendingmachine.utils.Product;

import javax.print.attribute.standard.RequestingUserName;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import static java.util.concurrent.TimeUnit.*;

public class ListProductController {

    private ArrayList<ProductComponents> pcs = new ArrayList<>();
    private AppController appController;
    private VendingMachineModel model;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final int DRINKS_X = 30;
    private final int DRINKS_Y = 60;
    private final int CHOCOLATES_X = 30;
    private final int CHOCOLATES_Y = 330;
    private final int CHIPS_X = 350;
    private final int CHIPS_Y = 60;
    private final int CANDIES_X = 350;
    private final int CANDIES_Y = 330;

    private ScheduledExecutorService timer;


    @FXML
    private AnchorPane page;

    @FXML private Label wrongMessage;

    @FXML
    private Button cancelButton;

    // TODO: Cancel transaction record
    public void cancelTransaction(ActionEvent event) throws IOException {
        timer.shutdownNow();

        CancelledOrder cancelledOrder = new CancelledOrder();
        cancelledOrder.setId(cancelledOrder.getNextId());

        if (model.getCurrentUser() == null) {
            cancelledOrder.setUsername("anonymous");
        } else {
            cancelledOrder.setUsername(model.getCurrentUser().toString());
        }
        cancelledOrder.setReason("user cancelled transaction");
        cancelledOrder.addCancelOrder();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
        root = loader.load();

        AppController appControl = loader.getController();
        appControl.init();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cancelOrder() {
        CancelledOrder cancelledOrder = new CancelledOrder();
        cancelledOrder.setId(cancelledOrder.getNextId());

        if (model.getCurrentUser() == null) {
            cancelledOrder.setUsername("anonymous");
        } else {
            cancelledOrder.setUsername(model.getCurrentUser().toString());
        }
        cancelledOrder.setReason("timeout");
        cancelledOrder.addCancelOrder();
    }

    public void generateOrder(ActionEvent event) throws IOException {
        timer.shutdownNow();
        Order order = new Order(appController.getModel().getCurrentUser());
        boolean noItem = true;
        for (ProductComponents pc : pcs) {
            //check product quantity if zero print the message
            if (pc.getProduct().getItemQuantity() - pc.getSpinner().getValue() < 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Request");
                alert.setContentText("Sorry, we do not have enough item to sell! Please check your quantity and try again!");
                alert.showAndWait();
                return;
            }else if(pc.getSpinner().getValue() == 0){
                continue;
            } else{
                Product temp = pc.getProduct();
                order.addProduct(new Product(temp.getItemCode(), temp.getItemName(), temp.getItemPrice(),
                        temp.getItemCategory(), pc.getSpinner().getValue()));
                noItem = false;
            }

        }

        if(noItem){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Selection");
            alert.setContentText("Have you selected the item you want to buy?");
            alert.showAndWait();
            return;
        }

        //change to order page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/CheckOrder.fxml"));
        root = loader.load();
        GenerateOrderController generateOrderControl = loader.getController();
        generateOrderControl.setModel(order);
        generateOrderControl.init(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void init(AppController appController) {
        this.appController = appController;
        this.model = appController.getModel();

        // list all products
        for (int i = 0; i < model.getProducts().size(); i++) {
            Product product = model.getProducts().get(i);
            ProductComponents pc;
            if (product.getItemCategory().equals("drinks")) {
                pc = new ProductComponents(product, DRINKS_X, DRINKS_Y +
                        (int)pcs.stream().filter(each->each.getProduct().
                                getItemCategory().equals("drinks")).count() * 40);
            } else if (product.getItemCategory().equals("chocolates")) {
                pc = new ProductComponents(product, CHOCOLATES_X, CHOCOLATES_Y +
                        (int)pcs.stream().filter(each->each.getProduct().
                                getItemCategory().equals("chocolates")).count()* 40);
            } else if (product.getItemCategory().equals("chips")) {
                pc = new ProductComponents(product, CHIPS_X,  CHIPS_Y+
                        (int)pcs.stream().filter(each->each.getProduct().
                                getItemCategory().equals("chips")).count() * 40);
            } else {
                pc = new ProductComponents(product, CANDIES_X, CANDIES_Y +
                        (int)pcs.stream().filter(each->each.getProduct().
                                getItemCategory().equals("candies")).count() * 40);
            }

            for (int j = 0; j < pc.getElements().size(); j++) {
                page.getChildren().add(pc.getElements().get(j));
            }
            pcs.add(pc);
        }

        // Add all category name labels
        Label drinksLabel = new Label();
        drinksLabel.setLayoutX(DRINKS_X);
        drinksLabel.setLayoutY(DRINKS_Y-40);
        drinksLabel.setText("Drinks");
        page.getChildren().add(drinksLabel);

        Label chocolatesLabel = new Label();
        chocolatesLabel.setLayoutX(CHOCOLATES_X);
        chocolatesLabel.setLayoutY(CHOCOLATES_Y-40);
        chocolatesLabel.setText("Chocolates");
        page.getChildren().add(chocolatesLabel);

        Label chipsLabel = new Label();
        chipsLabel.setLayoutX(CHIPS_X);
        chipsLabel.setLayoutY(CHIPS_Y-40);
        chipsLabel.setText("Chips");
        page.getChildren().add(chipsLabel);

        Label candiesLabel = new Label();
        candiesLabel.setLayoutX(CANDIES_X);
        candiesLabel.setLayoutY(CANDIES_Y-40);
        candiesLabel.setText("Candies");
        page.getChildren().add(candiesLabel);

        // Timer for 2 minutes
        timer = new ScheduledThreadPoolExecutor(1);

        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(()->{
                    cancelButton.fire();
                    cancelOrder();
                });
            }
        },2,MINUTES);
    }
}
