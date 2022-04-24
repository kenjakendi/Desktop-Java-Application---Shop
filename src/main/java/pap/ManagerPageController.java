package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
