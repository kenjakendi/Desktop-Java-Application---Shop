package pap;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class FinanceMonitorController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PieChart pieChart;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBinquiry db = new DBinquiry();
        Vector <Double> incoms = new Vector<Double>();
        try {
            incoms.add(db.getTransaction(Warehouse.getLASTid()));
            incoms.add(db.getTransaction(Warehouse.getLASTid()-1));
            incoms.add(db.getTransaction(Warehouse.getLASTid()-2));

        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<PieChart.Data> pieChartData = null;

            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(String.valueOf(Warehouse.getLASTid()), incoms.get(0)),
                    new PieChart.Data(String.valueOf(Warehouse.getLASTid()-1), incoms.get(1)),
                    new PieChart.Data(String.valueOf(Warehouse.getLASTid()-2), incoms.get(2)));





        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " zarobek: ", data.pieValueProperty()
                        )
                )
        );

        pieChart.getData().addAll(pieChartData);

    }

    public void switchToMainShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToManagerPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManagerPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
