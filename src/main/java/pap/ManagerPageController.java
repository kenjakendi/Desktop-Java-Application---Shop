package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerPageController {
    @FXML
    private TextField managerName;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeName(String name){
        managerName.appendText(name + " !");
    }


    public void switchToFinanceMonitor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FinanceMonitor.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEditOffer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangeOffer.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMagasinView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MagasinView.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPlaceOrder(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PlaceOrder.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
