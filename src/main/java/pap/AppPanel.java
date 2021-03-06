package pap;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppPanel extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainShop.fxml"));
        stage.setTitle("Shop Window");
//        stage.setFullScreen(true);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {


            DBinquiry db = new DBinquiry();

            Map<Item, Integer> items = new HashMap<>(db.getALLProduct());
            Warehouse warehouse = new Warehouse(items);
            warehouse.setLASTid(db.getLastId());


            Basket basket = new Basket(warehouse);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {

                    warehouse.dropAllitemsToBase();
                    //db.insertStatistics(2,1.1);
                    System.out.println(db.getLastId());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }));

            launch(args);


    }

}