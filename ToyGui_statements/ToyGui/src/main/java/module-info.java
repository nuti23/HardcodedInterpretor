module master {
    requires javafx.controls;
    requires javafx.fxml;


    opens master.sample to javafx.fxml;
    exports master.sample;
}