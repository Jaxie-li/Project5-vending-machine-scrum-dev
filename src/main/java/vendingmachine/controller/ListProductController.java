package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vendingmachine.components.ProductComponents;
import vendingmachine.model.VendingMachineModel;
import vendingmachine.utils.Product;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListProductController {
    private AppController appController;
    private VendingMachineModel model;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane drinks;
    @FXML
    private AnchorPane chocolates;
    @FXML
    private AnchorPane chips;
    @FXML
    private AnchorPane candies;
//    @FXML
//    private Label mineralWaterLabel, spriteLabel, cocacolaLabel, pepsiLabel, juiceLabel,
//            marsLabel, mmLabel, bountyLabel, snickersLabel,
////            smithsLabel, pringlesLabel, kettleLabel, thinsLabel,
////            mentosLabel, sourPatchLabel, skittlesLabel;
//    @FXML
//    private Spinner<Integer> mineralWater;

    public void cancelTransaction(ActionEvent event) throws IOException {
        System.out.println("You need to record this event!!! Edit /controller/ListProductController Line 29");
        /*
        Below should record this cancel and record it in model
         */



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
        root = loader.load();
        loader.setController(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void init(AppController appController, Parent root) {
        this.appController = appController;

        this.model = appController.getModel();

        for (int i = 0; i < model.getProducts().size(); i++) {
            Product product = model.getProducts().get(i);
//            ProductComponents pc = new ProductComponents(product,20,100+i*40);
            if (product.getItemCategory().equals("drinks")) {
//                System.out.println(String.format("drinks: %s %s".formatted(drinks.getLayoutX(),drinks.getLayoutY())));
                ProductComponents pc = new ProductComponents(product,(int)drinks.getLayoutX(),(int)drinks.getLayoutY()+i*40);
                drinks.getChildren().add(pc.getProductLabel());
                drinks.getChildren().add(pc.getSpinner());
            } else if (product.getItemCategory().equals("chocolates")) {
                ProductComponents pc = new ProductComponents(product,(int)chocolates.getLayoutX(),(int)chocolates.getLayoutY()+(i-drinks.getChildren().size())*40);
                chocolates.getChildren().add(pc.getProductLabel());
                chocolates.getChildren().add(pc.getSpinner());
            } else if (product.getItemCategory().equals("chips")) {
                ProductComponents pc = new ProductComponents(product,(int)chips.getLayoutX(),(int)chips.getLayoutY()+(i%9)*40);
                chips.getChildren().add(pc.getProductLabel());
                chips.getChildren().add(pc.getSpinner());
            } else if (product.getItemCategory().equals("candies")) {
                ProductComponents pc = new ProductComponents(product,(int)candies.getLayoutX(),(int)candies.getLayoutY()+(i-drinks.getChildren().size()-chips.getChildren().size())*40);
                candies.getChildren().add(pc.getProductLabel());
                candies.getChildren().add(pc.getSpinner());
            }
        }




//        // set the value of quantity of items to be 0~15 inclusive
//        SpinnerValueFactory<Integer> valueFactory =
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15);
//        valueFactory.setValue(0);
//

//
//        ArrayList<Product> products = model.getProducts();
//
//        for (int i = 0; i < products.size(); i++) {
//            Label label = new Label(products.get(i).toString());
//            label.setLayoutX(20);
//            label.setLayoutY(100+i*20);
//            Spinner<Integer> spinner = new Spinner<Integer>();
//            spinner.setValueFactory(valueFactory);
//
//
//        }
//
////        mineralWaterLabel.setText();
//
//        mineralWater.setValueFactory(valueFactory);

    }


}
