package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import vendingmachine.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListProductController {
    private AppController appController;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Spinner<Integer> mineralWater;

    public void cancelTransaction(ActionEvent event) throws IOException {
        System.out.println("You need to record this event!!! Edit /controller/ListProductController Line 29");
        /*
        Below should record this cancel and record it in model
         */



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/App.fxml"));
        root = loader.load();
        loader.setController(appController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void init(AppController appController){
        this.appController = appController;

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15);
        valueFactory.setValue(0);

        mineralWater.setValueFactory(valueFactory);
    }


}
