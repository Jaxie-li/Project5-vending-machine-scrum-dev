package vendingmachine.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vendingmachine.utils.Order;
import vendingmachine.utils.Product;

import java.io.IOException;
public class GenerateOrderController {
    private AppController appController;

    private Order model;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Product> orderTv;

    @FXML
    private TableColumn<Product, String> nameTc;

    @FXML
    private TableColumn<Product, Integer> quantityTc;

    @FXML
    private TableColumn<Product, String> priceTc;

    @FXML
    private Text totalTxt;

    private double calculateItemCost(Product p){
        return (double) p.getItemPrice() / 100;
    }

    public void init(AppController appController){
        this.appController = appController;
        nameTc.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityTc.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        priceTc.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", calculateItemCost(cellData.getValue()))));

        ObservableList<Product> oProdList = FXCollections.observableArrayList(model.getProducts());

        orderTv.setItems(oProdList);

        totalTxt.setText("Total Price:  $" + (double) model.getOrderTotal() / 100);
    }

//    @FXML
//    public void ReturnFrontPage(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/ListProduct.fxml"));
//        root = loader.load();
//        loader.setController(appController);
//        init(appController);
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public void returnFrontPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/ListProduct.fxml"));
        root = loader.load();
        ListProductController listProductController  = loader.getController();
        listProductController.init(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //when click pay button then can choose payment cash.
    public void switchToCash(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/CashPayment.fxml"));
        root = loader.load();
        CashPaymentController cashPaymentController = loader.getController();
        model.setPaymentMethod("cash");
        cashPaymentController.setModel(model);
        cashPaymentController.init(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //when click pay button then can choose payment cash or cash.
    public void switchToCard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/CardPayment.fxml"));
        root = loader.load();
        CardPaymentController cardPaymentController = loader.getController();
        model.setPaymentMethod("card");
        cardPaymentController.setModel(model);
        cardPaymentController.init(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Order getModel() {
        return model;
    }

    public void setModel(Order model) {
        this.model = model;
    }
}
