package pap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainShopController implements Initializable {
    @FXML
    private ListView<String> basketView;
    @FXML
    Label test;

    private Stage stage;
    private Scene scene;
    private Parent root;
    static Basket basket = new Basket();
    private String catchItemName;
    @FXML
    Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshScene();
        basketView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                catchItemName = basketView.getSelectionModel().getSelectedItem();
                test.setText(catchItemName);
            }
        });
    }

    public void refreshScene(){
        basketView.getItems().clear();
        for (Item item: basket.basket.keySet())
        {
            String name = item.getName();
            basketView.getItems().add(name);
        }
        logOutButton.setVisible(LogInController.getLogged());
    }

    public void addItem(Item item){
        basket.addItem(item);
        refreshScene();
    }

    public void remove(ActionEvent event) throws IOException{
        if (LogInController.getLogged()) {
            try {
                Item item = basket.findItemByName(catchItemName);
                basket.removeItem(item);
                refreshScene();
            } catch (Exception e) {
                System.out.println("no product chosen");
            }
        }
        else{
            waitForManager();
        }
    }

    public void waitForManager(){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Alert Alarm Emergency");
        Label label= new Label("Wait for manager to come");
        Button button1= new Button("Close this pop up window");
        button1.setOnAction(e -> popUpWindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 200, 200);
        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();
    }

    public void switchToPay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Pay.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogIn(ActionEvent event) throws IOException {
        String resource;
        if (LogInController.getLogged()) {
            resource = "ManagerPage.fxml";
        }
        else {
            resource = "LogIn.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Articles.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logOut(){
        LogInController.setLogged(false);
        logOutButton.setVisible(false);
    }
}
