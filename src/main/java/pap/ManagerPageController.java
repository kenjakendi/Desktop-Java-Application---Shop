package pap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ManagerPageController {
    @FXML
    private TextField managerName;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeName(String name){
        managerName.appendText(name + " !");
    }

    public void switchToHandlingOfGoods(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HandlingOfGoods.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToFinanceMonitor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FinanceMonitor.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToMainShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainShop.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
