module pap {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    opens pap to javafx.fxml;
    exports pap;
}