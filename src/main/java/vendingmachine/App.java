package vendingmachine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import vendingmachine.controller.AppController;

/**
 * Main Entrance of all the program
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/App.fxml"));
        Parent root = loader.load();
        AppController mainController = loader.getController();
        mainController.init();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Lite Snacks Vending Machine");
        stage.setResizable(false);
        stage.show();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e){
                try {
                    System.exit(0);
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        stage.show();
    }
}
