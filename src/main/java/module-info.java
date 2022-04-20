module pap22 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens pap to javafx.fxml;
    exports pap;
}