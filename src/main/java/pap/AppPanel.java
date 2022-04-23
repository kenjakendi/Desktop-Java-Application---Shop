package pap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppPanel extends Application {
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("MainShop.fxml"));
        stage.setTitle("Shop");
        stage.setScene(new Scene(root));
        stage.show();
        Basket our_basket = new Basket();
    }

    public static void main(String[] args) {
        launch();
    }

}