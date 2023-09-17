module assignment2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens assignment2 to javafx.fxml;
    exports assignment2;
}
