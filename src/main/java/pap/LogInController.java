package pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Logger;

public class LogInController {

    DBinquiry logininquiry = new DBinquiry();

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public TextField login;
    @FXML
    private TextField password;
    @FXML
    private Label loginStatus;
    static public boolean logged = false;

    public void switchToMainShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainShop.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setLogged(boolean status){
        logged = status;
    }

    public void switchToManagerPage(String log, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerPage.fxml"));
        root = loader.load();
        ManagerPageController managerPage = loader.getController();
        managerPage.changeName(log);
        setLogged(true);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean getLogged(){
        return logged;
    }

    public  String getHashSHA(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < byteData.length; i++){
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map tryLogging(ActionEvent event) throws IOException {
        String log = login.getText();
        String pass = password.getText();
        pass = getHashSHA(pass);
        Manager our_manager = new Manager(log, pass);
        System.out.println(our_manager.userData.keySet() + " " + our_manager.userData.values());

        try{
            if(this.logininquiry.isLogin(login.getText(), pass)){
                switchToManagerPage(log, event);
            }
            else
                this.loginStatus.setText("Złe dane");
        } catch (Exception localException){
            System.out.println("eerrr");
        }
        return our_manager.userData;
    }

    public void switchToHelp(){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setWidth(550);
        popUpWindow.setHeight(300);
        popUpWindow.setTitle("Help");
        Label label1= new Label("    Ta strona jest wyłącznie dla menedżera. \nProszę wpisać swoją nazwę użytkownika i hasło \n        lub wróć do strony głównej sklepu.");
        label1.setTextFill(Color.rgb(162, 92, 0));
        label1.setFont(Font.font ("Verdana", 20));
        Button button1= new Button("Zamknij");
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

}
