package vendingmachine.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

public class Owner {
    @FXML private Button back;
    @FXML private Button add;
    @FXML private Button delete;

    private Stage stage;
    private Scene scene;

    public void ownerMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vendingmachine/GUI/Owner.fxml"));
        Parent root = loader.load();
        stage = (Stage) (back.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
