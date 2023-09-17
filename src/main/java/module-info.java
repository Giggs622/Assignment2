module assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens assignment2 to javafx.fxml;
    exports assignment2;
}
