package vendingmachine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import vendingmachine.utils.User;

import java.io.IOException;
import java.util.Optional;

public class OwnerDeleteController {

    @FXML
    private TextField removedUserName;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private AppController appController;

    public void init(AppController appController) {
        this.appController = appController;
    }

    @FXML
    void returnOwnerMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/Owner.fxml"));
        root = loader.load();
        OwnerController ownerController = loader.getController();
        ownerController.init(appController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deleteAccount(ActionEvent event) throws IOException {

        if (removedUserName.getText().length() > 0) {

            Boolean foundFlag = false;
            User pairedUser = null;
            for (User user : appController.getModel().getUsers()) {

                if (user.getUsername().equals(removedUserName.getText())) {
                    foundFlag = true;
                    pairedUser = user;
                }
            }

            if (foundFlag) {
                if (pairedUser.getUserType().equals("owner")) {
                    System.out.println("Owner wants to remove himself");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Remove Error :( ");
                    alert.setContentText("You can't remove your account :<");

                    alert.showAndWait();
                } else {
                    deleteSuccessful(event);
                }

            } else {
                System.out.println("User not found ! ");
                userNotFound(event);
            }
        } else {
            System.out.println("No input");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Please input a user name :) ");
            alert.setContentText("Enter a name !!!!!!!!!!! ");
            alert.showAndWait();
        }
    }

    private void userNotFound(ActionEvent event) throws IOException {

        System.out.println("Can not find " + removedUserName.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Username: " + removedUserName.getText() + " NOT FOUND 404");
        alert.setHeaderText("Checking User Name :) ");
        alert.setContentText("Can't find the user ! ");

        alert.showAndWait();

    }

    private void deleteSuccessful(ActionEvent event) throws IOException {
        System.out.println("Owner wants to remove " + removedUserName.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remove " + removedUserName.getText());
        alert.setHeaderText("Removing :) ");
        alert.setContentText("Do you want to delete ? ");

        Optional<ButtonType> button = alert.showAndWait();

        if (button.isEmpty()) {
            System.out.println("Close the pop up window");

        } else if (button.get() == ButtonType.OK) {
            System.out.println("Click OK >> Remove successfully");

            removeAccount(removedUserName.getText());
            int removedUserNameIndex = 0;
            for (User user: appController.getModel().getUsers()) {
                removedUserNameIndex ++;
                if (user.getUsername().equals(removedUserName.getText())) {
                    break;
                }
            }
            appController.getModel().getUsers().remove(removedUserNameIndex - 1);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vendingmachine/GUI/OwnerDelete.fxml"));
            root = loader.load();
            OwnerDeleteController ownerDeleteController = loader.getController();
            ownerDeleteController.init(appController);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * remove an account and update the user.json
     */
    private void removeAccount(String removedAccountName) {
        JSONObject tmp = new JSONObject();
        tmp.put("username", removedAccountName);

        User.delete(User.read(User.path), tmp, User.path, "username");
    }
}
