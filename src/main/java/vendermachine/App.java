package vendermachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vendermachine.controller.AppController;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendermachine/App.fxml"));
        Parent root = loader.load();
        AppController mainController = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Lite Snacks Vending Machine");
        stage.setResizable(false);
        stage.show();

        stage.setScene(scene);
        stage.show();
    }
}
