package pap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.CycleMethod;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.layout.BackgroundFill;
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
        popUpWindow.setWidth(400);
        popUpWindow.setTitle("Alert Alarm Emergency");
        Label label1= new Label("          Proszę czekać, \nmenedżer przyjdzie za chwilę");
        label1.setTextFill(Color.rgb(162, 92, 0));
        label1.setFont(Font.font ("Verdana", 20));
        Button button1= new Button("Zamknij to okno");
        button1.setBackground(new Background(new BackgroundFill(Color.rgb(162, 92, 0), new CornerRadii(0), Insets.EMPTY)));
        button1.setTextFill(Color.rgb(200, 255, 240));
        button1.setOnAction(e -> popUpWindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(new LinearGradient(0,0,0,1,true, CycleMethod.NO_CYCLE, new Stop(0.1f, Color.rgb(231, 126, 111, .4)),
                new Stop(1.0f, Color.rgb(254, 249, 229, .4))), new CornerRadii(0), Insets.EMPTY)));
        Scene scene1 = new Scene(layout, 200, 200);
        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();
    }

    public void switchToHelp(){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setWidth(550);
        popUpWindow.setHeight(300);
        popUpWindow.setTitle("Help");
        Label label1= new Label("Jeśli chcesz dodać artykuł do swojego koszyka, \n            przejdź na stronę wyszukiwania, \n wciskając <<Wyszukaj artykuł po nazwie>>.");
        label1.setTextFill(Color.rgb(162, 92, 0));
        label1.setFont(Font.font ("Verdana", 15));

        Button button1= new Button("Zamknij");
        button1.setBackground(new Background(new BackgroundFill(Color.rgb(162, 92, 0), new CornerRadii(0), Insets.EMPTY)));
        button1.setTextFill(Color.rgb(200, 255, 240));
        button1.setOnAction(e -> popUpWindow.close());

        Label label2= new Label("Jeśli chcesz zawołać menedżera, wciśnij <<Pomoc asystenta>>.");
        label2.setTextFill(Color.rgb(162, 92, 0));
        label2.setFont(Font.font ("Verdana", 15));

        Button button2= new Button("Pomoc asystenta");
        button2.setBackground(new Background(new BackgroundFill(Color.rgb(162, 92, 0), new CornerRadii(0), Insets.EMPTY)));
        button2.setTextFill(Color.rgb(200, 255, 240));
        button2.setOnAction(e -> {popUpWindow.close(); waitForManager();});
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1, label2, button2);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(new LinearGradient(0,0,0,1,true, CycleMethod.NO_CYCLE, new Stop(0.1f, Color.rgb(231, 126, 111, .4)),
                new Stop(1.0f, Color.rgb(254, 249, 229, .4))), new CornerRadii(0), Insets.EMPTY)));
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
