package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OwnerAddController {

    @FXML
    private Button add;
    @FXML
    private Button back;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private AppController appController;

    public void init(AppController appController) {
        this.appController = appController;
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

}